public class VarExpr implements Expression{
    final String name;
 
    public VarExpr(String name){
	this.name=name;
    }
    
    @Override
    public Expression subst(String var, Expression rep){
	return var.equals(name)?rep:this;
    }

    @Override
    public String toString(){
	return name;
    }
}
					     
