namespace EntityLayer.Dtos.Event;

public class EventUpdateDto
{
    public string EventName { get; set; } = String.Empty;

    public string EventDescription { get; set; } = String.Empty;

    public DateTime EventDate { get; set; }
}