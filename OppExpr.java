public class OppExpr implements Expression{
    private final Expression expr;

    public OppExpr(Expression expr){
	this.expr=expr;
    }

    @Override
    public Expression subst(String var, Expression exp){
	return OppExpr.Of(exp);
    }

    public static Expression Of(Expression op){
	if(op instanceof ConstExpr)
	    return new ConstExpr(-((ConstExpr)op).getVal());
	else
	    return new OppExpr(MultiExpr.Of(new ConstExpr(-1),op));
    }

    @Override
    public String toString(){
	return "("+expr+")";
    }
}
