
package Outils;

import java.awt.Component;
import java.awt.Window;
import java.lang.reflect.Field;

public class Outils {

    @SuppressWarnings("unchecked")
    static public <T extends Component> T getComponentByName(Window window, String name) {

        // loop through all of the class fields on that form
        for (Field field : window.getClass().getDeclaredFields()) {

            try {
                // let us look at private fields, please
                field.setAccessible(true);

                // compare the variable name to the name passed in
                if (name.equals(field.getName())) {

                    // get a potential match (assuming correct &lt;T&gt;ype)
                    final Object potentialMatch = field.get(window);

                    // cast and return the component
                    return (T) potentialMatch;
                }

            } catch (SecurityException | IllegalArgumentException 
                    | IllegalAccessException ex) {

                // ignore exceptions
            }

        }

        // no match found
        return null;
    }

}