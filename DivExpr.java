public class DivExpr implements Expression{
    private final Expression droite;
    private final Expression gauche;

    public DivExpr(Expression gauche,Expression droite){
	this.droite=droite;
	this.gauche=gauche;
    }

    @Override
    public Expression subst(String var, Expression exp){
	Expression na=droite.subst(var,exp);
	Expression nb=gauche.subst(var,exp);
	return  DivExpr.Of(na,nb);
    }

    public static Expression Of(Expression op1, Expression op2){
	if(op1 instanceof ConstExpr && op2 instanceof ConstExpr)
	    return new ConstExpr(((ConstExpr)op1).getVal() /((ConstExpr)op2).getVal());
	else
	    return new DivExpr(op1,op2);
    }

    @Override
    public String toString(){
	return "("+gauche+"/"+droite+")";
    }
}
