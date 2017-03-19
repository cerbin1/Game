package app.view.render;

import static app.view.ImageRepository.imageRepository;

public class NobilityRenderer extends FigureRenderer {
    public NobilityRenderer(NobilityVO nobilityVO) {
        super(nobilityVO, imageRepository().nobility);
    }
}
