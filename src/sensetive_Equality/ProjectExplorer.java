package sensetive_Equality;


import java.io.File;

public class ProjectExplorer {
    
	public interface FileHandler {
        void handle(int level, String path, File file);
    }

    public interface Filter {
        boolean interested(int level, String path, File file);
    }

    private FileHandler fileHandler;
    private Filter filter;

    public ProjectExplorer(Filter filter, FileHandler fileHandler) {
        this.filter = filter;
        this.fileHandler = fileHandler;
    }

    public void explore(File root) {
    	explore(0, "", root);
    }

    private void explore(int level, String path, File file) {
        
    	if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                explore(level + 1, path + "/" + child.getName(), child);
            }
        } else {
            if (filter.interested(level, path, file)) {
                fileHandler.handle(level, path, file);
            }
        }
    }
}


Kishan vai er sathe kotha holo.

Theoritical kisu lkha lagbe na obosshoi ... BUT

archetype - kisu kotha lkha lagbe.. (ki lkha lagbe boi dkho)

class er gula te - keno konta kon archetype er under a egla explain korte hbe.

Onnanno diagram a - jodi tomar akano diagram dkhe kisu bujha na jay then explain kore lkhte hbe... bujha gele lkha lagbe na :p

User Interface:

task analysis, user analysis egla question a ashle lkhte hbe ..

Khatay boshe boshe user interface er screen akaite hobe :)

tansition gula jodi dkhano lage .. kothai click korle kon screen a jay..egla likhe dwa lagbe ( qstn a transition mne hoy dei ni ..kotha bole ja bujlam :p .. but tao... )

best of luck :) :) my friends.