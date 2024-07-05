using EntityLayer.Concrete;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer.Abstract
{
    public interface IWorkshopDal
    {
        List<Workshop> ListWorkshop();
        void AddWorksop(Workshop workshop);
        void DeleteWorkshop(Workshop workshop);
        void UpdateWorkshop(Workshop workshop);
        Workshop GetWorkshop(int id);
    }
}
