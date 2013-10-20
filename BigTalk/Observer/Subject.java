public interface Subject
{
	public void Attach(Observer observer);
	public void Detach(Observer observer);
	public void Notify();

	public String getSubjectState();
	public void setSubjectState(String action);
}
