import java.io.Serializable;

public class Players implements Serializable {
    private String name;
    private String size;
    private String found;

    private String timelim;
    private String time;
    private String topic;
    private String helps;

    public String getName(){return name;}
    public String getSize(){return size;}
    public String getFound(){return found;}
    public String getTimelim(){return timelim;}
    public String getTime(){return time;}
    public String getTopic(){return topic;}
    public String getHelps(){return helps;}

    public void setName(String name){this.name = name;}
    public void setSize(String size){this.size = size;}
    public void setTime(String time){this.time = time;}
    public void setTopic(String topic){this.topic = topic;}
    public void setHelps(String helps){this.helps = helps;}

    public Players(String name, String size, String found, String timelim, String time, String topic, String helps){
        this.name = name;
        this.size = size;
        this.found = found;
        this.timelim = timelim;
        this.time = time;
        this.topic = topic;
        this.helps = helps;
    }


}
