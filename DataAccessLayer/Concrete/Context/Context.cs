using EntityLayer.Concrete;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessLayer.Concrete.Context
{
    public class Context : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Data Source=localhost;Initial Catalog=GecekoduDB;Integrated Security=True;TrustServerCertificate=True;");
        }

        public DbSet<Event> Events { get; set; }

        public DbSet<Workshop> Workshops { get; set; }

        public DbSet<User> Users { get; set; }
    }
}