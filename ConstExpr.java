public class ConstExpr implements Expression{
    private double val;

    public ConstExpr(double val){
	this.val=val;
    }

    
    public double getVal(){
	return this.val;
    }
    @Override
    public Expression subst(String var,Expression rep){
	return this;
    }

    @Override
    public String toString(){
	return Double.toString(val);
    }
}
