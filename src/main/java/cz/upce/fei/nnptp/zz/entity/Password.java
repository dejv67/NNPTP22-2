package cz.upce.fei.nnptp.zz.entity;

import cz.upce.fei.nnptp.zz.entity.Parameter.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * Building passwords with parameters
 * @author Roman
 */
public class Password {

    private int id;
    private String password;
    private HashMap<String, Parameter> parameters;

    /**
     * Creates a password with empty map of parameters
     * @param id
     * @param password
     */
    public Password(int id, String password) {
        this.id = id;
        this.password = password;
        this.parameters = new HashMap<>();
    }

    /**
     * Creates a password with a map of parameters
     * @param id
     * @param password
     * @param parameters
     */
    public Password(int id, String password, HashMap<String, Parameter> parameters) {
        this.id = id;
        this.password = password;
        this.parameters = parameters;
    }

    /**
     * Returns password ID
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns all parameters
     * @return parameters
     */
    public HashMap<String, Parameter> getParameters() {
        return parameters;
    }

    boolean hasParameter(String TITLE) {
        return parameters.containsKey(TITLE);
    }

    public Parameter getParameter(String t) {
        return parameters.get(t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return id == password1.id && password.equals(password1.password) && parameters.equals(password1.parameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, parameters.hashCode());
    }

    /**
     * Converting password to a string
     * @return string
     */
    @Override
    public String toString() {

        String parametersString = "";
        int parameterIndex = 0;

        for (String type : parameters.keySet()) {

            String value;
            switch (type) {
                case Parameter.StandardizedParameters.EXPIRATION_DATETIME:
                    value = ((DateTimeParameter) parameters.get(type)).getValue().toString();
                    break;
                case Parameter.StandardizedParameters.TITLE:
                case Parameter.StandardizedParameters.WEBSITE:
                case Parameter.StandardizedParameters.DESCRIPTION:
                    value = ((TextParameter) parameters.get(type)).getValue();
                    break;
                default:
                    return null;
            }

            parameterIndex++;
            parametersString += "Parameter{type=" + type + ", value=" + value + "}";

            if (parameterIndex < parameters.size()) {
                parametersString += ",";
            }
        }

        return "Password{" + "id=" + id + ", password=" + password + ", parameters=" + parametersString + '}';
    }
}
