/**
 * 
 * Base exception class for representing exceptions that can
 * occur within a calculator.  
 * 
 * This class extends Exception, requiring only that message be given
 * to determine the issue.
 */
public class CalculatorException extends Exception
{
    public CalculatorException(String msg){
        super(msg);
    }
}
