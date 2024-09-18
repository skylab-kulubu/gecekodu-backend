using EntityLayer.Concrete;
using Microsoft.EntityFrameworkCore;

namespace DataAccessLayer.Concrete.Context
{
    public class Context : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseSqlServer(
                    "Server=sqlserver;Database=GeceKoduDb;User Id=sa;Password=12345678Aa.;TrustServerCertificate=True",
                    sqlServerOptions => sqlServerOptions.EnableRetryOnFailure()
                );
            }
        }

        public DbSet<Event> Events { get; set; }

        public DbSet<Workshop> Workshops { get; set; }

        public DbSet<User> Users { get; set; }

        public DbSet<UserWorkshop> UserWorkshops { get; set; }

        public DbSet<UserEvent> UserEvents { get; set; }
        public DbSet<OperationClaim> OperationClaims { get; set; }
        public DbSet<UserOperationClaim> UserOperationClaims { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Many to Many Relationship between User and Workshop
            modelBuilder.Entity<UserWorkshop>()
                .HasKey(sc => new { sc.UserID, sc.WorkshopID });

            modelBuilder.Entity<UserWorkshop>()
                .HasOne(sc => sc.User)
                .WithMany(s => s.UserWorkshops)
                .HasForeignKey(sc => sc.UserID);

            modelBuilder.Entity<UserWorkshop>()
                .HasOne(sc => sc.Workshop)
                .WithMany(c => c.UserWorkshops)
                .HasForeignKey(sc => sc.WorkshopID);

            // Many to Many Relationship between User and Event
            modelBuilder.Entity<UserEvent>()
                .HasKey(sc => new { sc.UserID, sc.EventID });

            modelBuilder.Entity<UserEvent>()
                .HasOne(sc => sc.User)
                .WithMany(s => s.UserEvents)
                .HasForeignKey(sc => sc.UserID);

            modelBuilder.Entity<UserEvent>()
                .HasOne(sc => sc.Event)
                .WithMany(c => c.UserEvents)
                .HasForeignKey(sc => sc.EventID);
        }
    }
}