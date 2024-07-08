using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer.Abstract
{
    public interface IGenericDal<X> where X : class
    {
        void Insert(X x);
        void Delete(X x);
        void Update(X x);
        List<X> GetAll(Expression<Func<X, bool>> filter = null);
        X Get(Expression<Func<X, bool>> filter);
    }
}
