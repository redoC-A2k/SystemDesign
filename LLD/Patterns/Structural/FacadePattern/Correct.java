package LLD.Patterns.Structural.FacadePattern;
import java.io.File;

// Facade is a structural design pattern that provides a simplified interface to a library, a framework, or any other complex set of classes. (Ref: R)
// Client interacts with Facade and Facade interacts with subsystems. However if client wants it can directly interact with subsystem. Hence facade is not mandatory (Ref: R)
// There can be client to facade and then facade to facade (and so on ...) communication occur

// When to use (Ref : S)
// When we have to hide system complexities from client (Ref : S)

// Real world Example:
// Car -> If we press accelerator car will move when we press brake car will stop. But we don't know how it is happening internally or the complexities behind accelarator and brake . (Ref: S)

// Pros:
// 1. It hides the complexities of the system and provides an interface to the client. (Ref: S)
// 2. It decouples the client from the subsystem. So any change in complex system logic will impact facade but not client (Ref: S)

// Cons:
// 1. A facade can become god object coupled to all classes in the system. (Ref: S)
// 2. If we have to add new functionality then we have to modify facade. (Ref: S)

// Comparison with Proxy:
// In proxy pattern , proxy is for a particular object and controlling access it

// Comparision with Adapter:
// Client and original interface is incompatible , so we use adapter to make them compatible. But in facade client and subsystem are compatible but we use facade to hide complexities of subsystem

// Example:
class VideoFile { // Complex logic
    private String name;
    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

    public String getCodecType() {  return codecType; }
    public String getName() { return name; }
}

interface Codec {} // Complex logic

class Mpeg4CompressionCodec implements Codec { // Complex logic
    public String type = "mp4";
}

class OggCompressionCodec implements Codec { // Complex logic
    public String type = "ogg";
}

class CodecFactory { // Complex logic
    public static Codec extract(VideoFile file){
        String type = file.getCodecType();
        if(type.equals("ogg")) {
            System.out.println("Extracting OggCompressionCodec");
            return new OggCompressionCodec();
        } else {
            System.out.println("Extracting Mpeg4CompressionCodec");
            return new Mpeg4CompressionCodec();
        }
    }
}

class BitrateReader { // complex logic
    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader: reading file...");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader: writing file...");
        return buffer;
    }
}

class AudioMixer { // Complex logic
    public File fix(VideoFile file){
        System.out.println("Fixing audio file");
        return new File("tmp");
    }
}

class VideoConversionFacade { // Facade
    public File convertVideo(String fileName, String format) {
        System.out.println("VideoConversionFacade: converting video file");
        VideoFile file = new VideoFile(fileName);
        Codec sourceCodec = CodecFactory.extract(file);
        Codec destinationCodec;
        if(format.equals("mp4")) {
            destinationCodec = new Mpeg4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }
        VideoFile buffer = BitrateReader.read(file, sourceCodec);
        VideoFile intermediateResult = BitrateReader.convert(buffer, destinationCodec);
        AudioMixer audioMixer = new AudioMixer();
        File result = audioMixer.fix(intermediateResult);
        System.out.println("Conversion completed");
        return result;
    }
}

class Main { // Client
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
    }
}