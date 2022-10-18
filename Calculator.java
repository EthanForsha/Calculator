/**
 * The Calculator class provides functionality for parsing input strings
 * that contain simple expressions and for computing the result of the expression.
 *
 */

public class Calculator 
{
    public Calculator()
    {
        // Constructor does nothing
    }

    /**
     * Compute the result of the expression encoded in a sequence of tokens.
     * 
     * Here are the different cases:
     * 0 tokens: IllegalInputException: "Illegal Token Length"
     * 1 token: 
     *      "quit" (any casing): QuitException
     *      All other cases: IllegalInputException: "Illegal Argument"
     * 2 tokens:
     *      "-" and any number: return negative of number
     *      "-" and not a number: IllegalInputException: "Illegal Argument"
     *      other string and any string: IllegalInputException: "Illegal Operator"
     * 3 tokens:
     *      number1, "+", number2: return sum of two numbers
     *      number1, "/", zero: DivideByZeroException
     *      number1, "/", not zero: return number1/number2
     *      not a number, anything, anything: IllegalInputException: "Illegal Argument"
     *      number1, anything, not a number: IllegalInputException: "Illegal Argument"
     *      number1, not an operator, number2: IllegalInputException: "Illegal Operator"
     * 4 or more tokens: IllegalInputException: "Illegal Token Length"
     *     
     * Note: all numbers are integers
     * 
     * @param tokens The array of tokens to evaluate
     * @return int result of evaluating the expression
     * @throws CalculatorException If some form of error has been generated or
     * "quit" has been typed. Throws one of the several child classes of CalculatorException
     * in order to give more information on what the error is.
     */
    public static int compute(String[] tokens) throws CalculatorException
    {
        int x = 0;
        // Condition on the number of tokens
        switch(tokens.length)
        {
            case 0:
                throw new IllegalInputException("Illegal Token Length");               
            case 1: // One token
                // Only case: quit
                if(tokens[0].equals("quit") || tokens[0].equals("Quit")){
                    throw new QuitException("Quitting");
                }
                else{
                    throw new IllegalInputException("Illegal Argument");
                }          
            case 2: // Two tokens
                // Only case: unary operator
                try
                {
                    x = Integer.parseInt(tokens[1]);
                    return x;
                }
                catch (NumberFormatException e){
                    throw new IllegalInputException("Illegal Argument");
                }
            case 3: // 3 tokens
                // Binary operator
                try
                {
                    int a = Integer.parseInt(tokens[0]);
                    int b = Integer.parseInt(tokens[2]);
                    if(tokens[1].equals("+"))
                    {
                        x = a + b;
                        return x;
                    }
                    if(tokens[1].equals("-"))
                    {
                        {
                            x = a - b;
                            return x;
                        }
                    }
                    if(tokens[1].equals("*"))
                    {
                        {
                            x = a * b;
                            return x;
                        }
                    }
                    if(tokens[1].equals("/"))
                    {
                        if(b == 0)
                        {
                            throw new DivideByZeroException("Tried to divide by zero");
                        }
                        else{
                            x = a / b;
                            return x;
                        }
                    }
                }
                catch(NumberFormatException e)
                {
                    throw new IllegalInputException("Illegal Argument");
                }          
            default:
                // 4 or more tokens
                throw new IllegalInputException("Illegal Token Length");   
        }
    }

    /**
     * Parse the input string as an expression and evaluate it.
     * 
     * If the input is a legal expression, then the result is printed
     * 
     * Otherwise a CalculatorException has occurred.  Print a message based on
     * what exception type it is.
     * 
     * Always print out what the input was. Use a finally block for this.
     * 
     * Always prints out two lines, following the rules:
     * 1st line:
     * -No Exception: "The result is: " + result
     * -QuitException: "Quitting"
     * -DivideByZeroException: "Tried to divide by zero"
     * -IllegalInputException: "Illegal input: " + illegalinputtype(given to constructor)
     * -CalculatorException: <should never happen> e.getMessage()
     * 2nd line:
     * "Input was: " + input
     * 
     * @param input A String possibly containing a mathematical expression
     * @return true if the String is equal to "quit"; false otherwise
     */
    public static boolean parseAndCompute(String input)
    {
        // Pull out the tokens
        String[] tokens = input.split(" ");
        try 
        {
            if(tokens.length == 2 && (tokens[0].equals("-") || tokens[0].equals("+"))){
                System.out.println("The result is: " + tokens[0] + compute(tokens));
            }
            else{
                System.out.println("The result is: " + compute(tokens));
            }   
        }
        catch (QuitException e)
        {
            System.out.println(e.getMessage());
            return true;
        }
        catch (IllegalInputException e)
        {
            System.out.println(e.getMessage());
        }
        catch (CalculatorException e)
        {
            // This catches the remaining CalculatorException case: DivideByZeroException
            System.out.println(e.getMessage());
        }
        System.out.print("Input was: ");
        for(String i : tokens){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();

        // Quit has not been specified
        return false;
    }
}
