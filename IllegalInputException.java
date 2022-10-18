public class IllegalInputException extends CalculatorException{
    private String illegalType;

    public IllegalInputException(String msg){
        super(msg);
        illegalType = msg;
    }

    public String getIllegalType(){
        return illegalType;
    }
}