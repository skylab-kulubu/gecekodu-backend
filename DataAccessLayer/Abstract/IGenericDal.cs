using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer.Abstract
{
    public interface IGenericDal<X> where X : class
    {
        void Insert(X x);
        void Delete(X x);
        void Update(X x);
        List<X> GetAll();
        X GetById(int id);
    }
}
