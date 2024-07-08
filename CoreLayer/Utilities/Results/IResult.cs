namespace CoreLayer.Utilities.Results;

public interface IResult
{ 
    bool Success { get; set; }
    string Message { get; set; }
}