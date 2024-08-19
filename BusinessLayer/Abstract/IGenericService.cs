using CoreLayer.Utilities.Results;

namespace BusinessLayer.Abstract;

public interface IGenericService<P>
{
    IDataResult<P> GetById(int id);
    IDataResult<List<P>> GetAll();
    IResult Add(P p);
    IResult Update(P p);
    IResult Delete(int id);
}