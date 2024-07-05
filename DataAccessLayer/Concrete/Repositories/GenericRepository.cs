using DataAccessLayer.Abstract;
using DataAccessLayer.Concrete.Context;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer.Concrete.Repositories
{
    public class GenericRepository<X> : IGenericDal<X> where X : class
    {
        Context context = new Context();
        DbSet<X> _object;

        public GenericRepository()
        {
            _object = context.Set<X>();
        }

        public void Delete(X x)
        {
            var deletedEntity = context.Entry(x);
            deletedEntity.State = EntityState.Deleted;
            context.SaveChanges();
        }

        public List<X> GetAll()
        {
            return _object.ToList();
        }

        public X GetById(int id)
        {
            throw new NotImplementedException();
        }

        public void Insert(X x)
        {
            var addedEntity = context.Entry(x);
            addedEntity.State = EntityState.Added;
            context.SaveChanges();
        }

        public void Update(X x)
        {
            var updatedEntity = context.Entry(x);
            updatedEntity.State = EntityState.Modified;
            context.SaveChanges();
        }
    }
}
