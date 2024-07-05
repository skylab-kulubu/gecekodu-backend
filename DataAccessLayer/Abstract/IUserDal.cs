using EntityLayer.Concrete;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer.Abstract
{
    public interface IUserDal
    {
        List<User> ListUser();
        void AddUser(User user);
        void DeleteUser(User user);
        void UpdateUser(User user);
        User GetUser(int id);
    }
}
