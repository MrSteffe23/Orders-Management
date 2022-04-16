package start;

import presentation.*;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 * Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {

    public static void main(String[] args) {
        ViewSelect viewselect = new ViewSelect();
        new Controller(viewselect);
    }
}
