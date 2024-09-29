namespace EntityLayer.Dtos.Event;

public class EventDto
{
    public int Id { get; set; }

    public string Name { get; set; } = String.Empty;

    public string Description { get; set; } = String.Empty;

    public DateTime Date { get; set; }
}