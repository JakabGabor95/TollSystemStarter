import hu.ltk.jakabgabor.TollSystemPersistenceStorage;
import hu.ltk.jakabgabor.TollSystemRegister;
import hu.ltk.jakabgabor.api.MotorwayVignetteResponseInterface;
import hu.ltk.jakabgabor.controller.TollSystemController;
import hu.ltk.jakabgabor.presenter.TollSystemPresenter;
import hu.ltk.jakabgabor.services.VehicleRegister;
import hu.ltk.jakabgabor.services.VehicleRegisterInteractor;
import hu.ltk.jakabgabor.storage.MotorwayVignetteStorageInterface;
import hu.ltk.jakabgabor.view.TollSystemView;

public class StarterMain {
    public static void main(String[] args) {
        TollSystemPresenter tollSystemPresenter = new TollSystemPresenter();
        MotorwayVignetteStorageInterface tollSystemPersistenceStorage = new TollSystemPersistenceStorage();

        TollSystemRegister motorwayVignetteRequestInterface =
                new TollSystemRegister(tollSystemPersistenceStorage,tollSystemPresenter );
        VehicleRegister vehicleRegister = new VehicleRegister();

        VehicleRegisterInteractor vehicleRegisterInteractor =
                new VehicleRegisterInteractor(motorwayVignetteRequestInterface, vehicleRegister);

        motorwayVignetteRequestInterface.setVehicleRegisterInteractor(vehicleRegisterInteractor);
        TollSystemController tollSystemController = new TollSystemController(motorwayVignetteRequestInterface);
        TollSystemView tollSystemView = new TollSystemView(tollSystemController);
        tollSystemPresenter.setTollSystemView(tollSystemView);
        tollSystemView.run();
    }
}
