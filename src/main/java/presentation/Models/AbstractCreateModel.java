package presentation.Models;

import bll.ClientBLL;
import bll.ProductBLL;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.*;
import java.util.List;

public class AbstractCreateModel<T> {

    private final Class<T> type; //T is a class(for example Client)

    @SuppressWarnings("unchecked")
    public AbstractCreateModel() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        //System.out.println(type+";\n"+type.getName()+";\n"+type.getSimpleName()+";\n"+type.getDeclaredFields()+";\n"+type.getClass());
    }

    /**
     * creates an empty model only with the columns name
     *
     * @return a default table model
     */
    public DefaultTableModel createEmptyModel() {
        int count = 0;
        String[] column = new String[type.getDeclaredFields().length - 1];
        for (Field field : type.getDeclaredFields()) {
            try {
                if (!field.getName().equals("id")) {
                    if (field.getName().equals("id_client"))
                        column[count] = "Client name";
                    else if (field.getName().equals("id_product"))
                        column[count] = "Product name";
                    else
                        column[count] = field.getName();
                    count++;
                }

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return new DefaultTableModel(column, 0);
    }

    /**
     * creates a model for a JTable, with column names and rows
     *
     * @param objects a list of objects from which to extract the data and populate after this the JTable
     * @return a default table model populated with data
     */
    public DefaultTableModel createModelTable(List<T> objects) {
        DefaultTableModel model = createEmptyModel();
        Object[] data = new Object[type.getDeclaredFields().length - 1];
        for (T obj : objects) {
            int index = 0;
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (!field.getName().equals("id")) {
                        if (field.getName().equals("id_client"))
                            data[index++] = new ClientBLL().findClientById((Integer) field.get(obj)).getName();
                        else if (field.getName().equals("id_product"))
                            data[index++] = new ProductBLL().findProductById((Integer) field.get(obj)).getName();
                        else
                            data[index++] = field.get(obj);
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(data);
        }
        return model;
    }
}
