public class PlusExpr implements Expression{
    private final Expression droite;
    private final Expression gauche;

    public PlusExpr(Expression droite,Expression gauche){
	this.droite=droite;
	this.gauche=gauche;
    }

    @Override
    public Expression subst(String var, Expression exp){
	Expression na=droite.subst(var,exp);
	Expression nb=gauche.subst(var,exp);
	return  PlusExpr.Of(na,nb);
    }

    public static Expression Of(Expression op1, Expression op2){
	if(op1 instanceof ConstExpr && op2 instanceof ConstExpr)
	    return new ConstExpr(((ConstExpr)op1).getVal() +((ConstExpr)op2).getVal());
	else
	    return new PlusExpr(op1,op2);
    }

    @Override
    public String toString(){
	return "("+gauche+"+"+droite+")";
    }
}
