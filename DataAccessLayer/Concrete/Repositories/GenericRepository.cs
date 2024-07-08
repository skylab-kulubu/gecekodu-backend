using DataAccessLayer.Abstract;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;
using EntityLayer.Abstract;

namespace DataAccessLayer.Concrete.Repositories
{
    public class GenericRepository<X> : IGenericDal<X> where X : class,IEntity
    {
        
        //needs some refactor omer its up to you, check my weblabtaskrepo for reference, hint: use using statement

        Context.Context context = new Context.Context();
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

        public List<X> GetAll(Expression<Func<X,bool>> filter = null)
        {
                return filter == null
                    ? _object.ToList()
                    : _object.Where(filter).ToList();
        }

        public X Get(Expression<Func<X,bool>> filter)
        {
            return context.Set<X>().SingleOrDefault(filter);
           
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
