package app.view.render.renderer;

import app.view.render.vo.NobilityVO;

import static app.view.ImageRepository.imageRepository;

public class NobilityRenderer extends FigureRenderer {
    public NobilityRenderer(NobilityVO nobilityVO) {
        super(nobilityVO, imageRepository().nobility);
    }
}
