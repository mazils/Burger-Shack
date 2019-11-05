package Shared;

import java.io.Serializable;

public class Burger implements Serializable {


        private static final long serialVersionUID = 1L;
        private String name;


        public Burger(String name)
        {
            this.name = name;
        }

        /**
         * Getter for burger name
         *
         * @return the burger name
         */
        public String getName()
        {
            return name;
        }

        /**
         * A string representation of a burger
         *
         * @return a string representation of a burger
         */
        @Override
        public String toString()
        {
            return "Shared.Burger: " + name;
        }


}
