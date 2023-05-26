import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class FileStructure {
    // declares the root node of the structure
    private NLNode<FileObject> root;

    /**
     * constructs the file structure from the given file object and sets it as the root node
     * @param fileObjectName
     * @throws FileObjectException
     */
    public FileStructure(String fileObjectName) throws FileObjectException {
        FileObject file = new FileObject(fileObjectName);
        root = new NLNode<>(file, null);
        if(file.isDirectory()) { // if the file exists then call the recursive algorithm
            recurFile(root);
        }
    }

    /**
     * takes in a parameter of the root node and constructs the file structure
     * @param r
     */
    private void recurFile(NLNode<FileObject> r) {
        FileObject f = r.getData();
        if(f.isFile()) { // if the file already exists in the structure then exit the recursive algorithm
            return;
        }
        Iterator<FileObject> iterator = f.directoryFiles();
        while(iterator.hasNext()) { // adds the file to the structure and then calls the recursive algorithm to check for the next file
            FileObject child = iterator.next();
            NLNode<FileObject> node = new NLNode<>(child, r);
            r.addChild(node);
            recurFile(node);
        }
    }

    /**
     * gets the root node of the file structure
     * @return the root node of the file structure
     */
    public NLNode<FileObject> getRoot() {
        return root;
    }

    /**
     * creates an array list to store the files in and calls the recursive algorithm to check if the files are of the type specified
     * @param type
     * @return the array list containing files of the type specified
     */
    public Iterator<String> filesOfType(String type) {
        List<String> files = new ArrayList<>();
        recurFilesOfType(root, type, files);
        return files.iterator();
    }

    /**
     * takes in the parameters of the file structure and type of file and looks for all the files of the type specified
     * @param r
     * @param type
     * @param list
     */
    private void recurFilesOfType(NLNode<FileObject> r, String type, List<String> list) {
        FileObject f = r.getData();
        String name = f.getLongName();
        int index = name.lastIndexOf(".");
        if(index > 0 && ("." + name.substring(index + 1)).equals(type)) { // if the file has the same type as the one specefied in the parameter add it to the array list
            list.add(f.getLongName());
        }
        Iterator<NLNode<FileObject>> iterator = r.getChildren();
        while(iterator.hasNext()) { // loops through all the files and calls the recursive method on all of them until there are none left to check
            recurFilesOfType(iterator.next(), type, list);
        }
    }

    /**
     * finds the file with the specified name from the parameter
     * @param name
     * @return the directory of the file if found, otherwise returns an empty string
     */
    public String findFile(String name) {
        Queue<NLNode<FileObject>> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) { // loop through all the files until there are none left to check
            NLNode<FileObject> r = queue.poll();
            FileObject f = r.getData();
            if(f.getName().equals(name)) { // if the file has same name as the one from the parameter returns the directory of the file
                return f.getLongName();
            }
            Iterator<NLNode<FileObject>> iterator = r.getChildren();
            while(iterator.hasNext()) { // loops through all the files and adds them to the queue
                queue.add(iterator.next());
            }
        }
        return "";
    }
}
