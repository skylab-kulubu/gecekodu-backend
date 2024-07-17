using DataAccessLayer.Abstract;
using Microsoft.EntityFrameworkCore;
using System.Linq.Expressions;
using EntityLayer.Abstract;

namespace DataAccessLayer.Concrete.Repositories
{
    public class GenericRepository<X> : IGenericDal<X> where X : class, IEntity
    {
        //needs some refactor omer it's up to you, check my weblabtaskrepo for reference, hint: use using statement
        DbSet<X> _object;

        //Context.Context context = new Context.Context();
        public GenericRepository()
        {
            using (var context = new Context.Context())
            {
                _object = context.Set<X>();
            }
        }

        public void Delete(X x)
        {
            using (var context = new Context.Context())
            {
                var deletedEntity = context.Entry(x);
                deletedEntity.State = EntityState.Deleted;
                context.SaveChanges();
            }
        }

        public List<X> GetAll(Expression<Func<X, bool>> filter)
        {
            using (var context = new Context.Context()) //using bağlantıyı yenileyerek güncel veriyi getirmemizi sağlar
            {
                return filter == null ? context.Set<X>().ToList() : context.Set<X>().Where(filter).ToList();
            }
        }

        public X Get(Expression<Func<X, bool>> filter)
        {
            using (var context = new Context.Context())
            {
                return context.Set<X>().SingleOrDefault(filter);
            }
        }

        public void Insert(X x)
        {
            using (var context = new Context.Context())
            {
                var addedEntity = context.Entry(x);
                addedEntity.State = EntityState.Added;
                context.SaveChanges();
            }
        }

        public void Update(X x)
        {
            using (var context = new Context.Context())
            {
                var updatedEntity = context.Entry(x);
                updatedEntity.State = EntityState.Modified;
                context.SaveChanges();
            }
        }
    }
}