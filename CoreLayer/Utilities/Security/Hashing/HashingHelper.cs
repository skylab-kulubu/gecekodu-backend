using System.Text;

namespace CoreLayer.Utilities.Security.Hashing;

public class HashingHelper
{
    public static void CreatePasswordHash(string password, out byte[] passwordHash, out byte[] passwordSalt)
        /*
          You can use the out keyword in two contexts:
          - As a parameter modifier, which lets you pass an argument to a method by reference rather than by value.
          - In generic type parameter declarations for interfaces and delegates, which specifies that a type parameter is covariant.
          The out keyword is especially useful when a method needs to return more than one value since more than one out parameter can be used e.g.
         */
    {
        using (var hmac = new System.Security.Cryptography.HMACSHA512())
        {
            passwordSalt = hmac.Key; // Every person has a unique key
            passwordHash = hmac.ComputeHash(Encoding.UTF8.GetBytes(password));
        }
    }

    public static bool VerifyPasswordHash(string password, byte[] passwordHash, byte[] passwordSalt)
    {
        using (var hmac = new System.Security.Cryptography.HMACSHA512(passwordSalt))
        {
            var computedHash = hmac.ComputeHash(Encoding.UTF8.GetBytes(password));
            for (int i = 0; i < computedHash.Length; i++)
            {
                if (computedHash[i] != passwordHash[i])
                {
                    return false;
                }
            }
        }

        return true;
    }
}