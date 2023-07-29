package 11package

import java.util.ArrayList;
import java.util.List;


//Command Invoker
public class ${invoker} {

    private List<${abstractCommand}> commands;//可以持有很多的命令对象

    public ${invoker}() {
        commands = new ArrayList();
    }

    public void addCommand(${abstractCommand} cmd){
        commands.add(cmd);
    }

    //TODO : Implement related methods to invoke command execution.: cmd.execute()
}