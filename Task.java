import java.time.LocalDate;
public class Task {
   private String title;
   private boolean isdone;
   private LocalDate dueDate;
   public Task(String title,LocalDate dueDate) {
      this.title = title;
      this.dueDate = dueDate;
      this.isdone = false;
   }
    public String getTitle() {
        return title;
    }
    public boolean isDone() {
        return isdone;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void markAsDone() {
        this.isdone = true;
    }
    public String toString() {
        String status=isdone ? "Done" : "Not Done";
        String due=(dueDate!=null)?"(Due: "+dueDate.toString()+")":"(No Due Date)";
        return "Task: "+ title+ "   "+"Due: "+due+"   "+"Status:"+status;
    }

}
