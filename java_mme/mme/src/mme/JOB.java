/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mme;

/**
 *
 * @author dan
 */
public class JOB
{
    private int inside_ID;
    private int inside_type;
    private String inside_arguments;
    
    JOB()
    {
        inside_ID = 0;
        inside_type = 0;
        inside_arguments = "";
    }
    
    JOB(int passedID, int passedType, String passedArguments)
    {
        inside_ID = passedID;
        inside_type = passedType;
        inside_arguments = passedArguments;
    }
    
    int get_ID()
    {
        return inside_ID;
    }
    
    void set_ID(int passed_ID)
    {
        inside_ID = passed_ID;
    }
    
    int get_type()
    {
        return inside_type;
    }
    
    void set_type(int passedType)
    {
        inside_type = passedType;
    }
    
    String get_args()
    {
        return inside_arguments;
    }
    
    void set_args(String passedArgs)
    {
        inside_arguments = passedArgs;
    }
}