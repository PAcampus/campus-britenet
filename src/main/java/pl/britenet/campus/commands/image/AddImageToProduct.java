package pl.britenet.campus.commands.image;

import pl.britenet.campus.Constants;
import pl.britenet.campus.commands.Command;
import pl.britenet.campus.services.ImageService;

import java.text.ParseException;

public class AddImageToProduct extends Command {
    private final ImageService imageService;

    public AddImageToProduct(ImageService imageService) {
        super(Constants.COMMAND_ADD_IMAGE_TO_PRODUCT);
        this.imageService = imageService;
    }

    @Override
    public void execute() throws ParseException {

    }
}
