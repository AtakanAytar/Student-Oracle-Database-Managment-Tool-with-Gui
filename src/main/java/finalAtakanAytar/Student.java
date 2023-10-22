package finalAtakanAytar;

public class Student {
    private int studentId;
    private String studentName;
    private int studentTuition;

    public Student(int studentId,String studentName , int studentTuition){
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentTuition = studentTuition;



    }

    public int getStudentId() {
        return studentId;
    }

    public int getStudentTuition() {
        return studentTuition;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentTuition(int studentTuition) {
        this.studentTuition = studentTuition;
    }

}
