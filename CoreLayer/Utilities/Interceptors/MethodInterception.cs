namespace CoreLayer.Utilities.Interceptors;

public class MethodInterception
{
    /* protected virtual void OnBefore(IInvocation invocation) { } // invocation means the method that is intercepted, like add, update, delete, get etc.
    protected virtual void OnAfter(IInvocation invocation) { }
    protected virtual void OnException(IInvocation invocation, System.Exception e) { }
    protected virtual void OnSuccess(IInvocation invocation) { }
    public override void Intercept(IInvocation invocation)
    {
        var isSuccess = true;
        OnBefore(invocation);
        try
        {
            invocation.Proceed();
        }
        catch (Exception e)
        {
            isSuccess = false;
            OnException(invocation, e);
            throw;
        }
        finally
        {
            if (isSuccess)
            {
                OnSuccess(invocation);
            }
        }
        OnAfter(invocation);
    }
    */
}
// This class is used in order to check all the methods int the project and see if they are working properly.
// Additionally, this class is key for our methods to work properly.