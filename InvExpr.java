public class InvExpr implements Expression{
    private final Expression expr;

    public InvExpr(Expression expr){
	this.expr=expr;
    }

    @Override
    public Expression subst(String var,Expression exp){
	return InvExpr.Of(exp);
    }

    public static Expression Of(Expression op){
	if(op instanceof ConstExpr){
	    if(((ConstExpr)op).getVal()==0)
		return (ConstExpr)op;
	    return new ConstExpr(1/((ConstExpr)op).getVal());
	}
	else
	    return new InvExpr(DivExpr.Of(new ConstExpr(1),op));
    }

    @Override
    public String toString(){
	return "("+expr+")";
    }
}
