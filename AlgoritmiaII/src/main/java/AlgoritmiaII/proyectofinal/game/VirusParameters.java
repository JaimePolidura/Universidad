package AlgoritmiaII.proyectofinal.game;

public class VirusParameters {
    final double transmissionPercent;
    final int lifetimeInDays;
    final int minNeighToBeSurrounded;
    final int maxInfectionDistance;

    /**
     * Virus parameters
     * @param transmissionPercent Percentage of transmissibility
     * @param lifetimeInDays      Life-time in a host until get immunity
     * @param minNeighToBeSurrounded
     * @param maxInfectionDistance
     */
    public VirusParameters(double transmissionPercent, int lifetimeInDays, int minNeighToBeSurrounded,
                           int maxInfectionDistance) {
        this.transmissionPercent = transmissionPercent;
        this.lifetimeInDays = lifetimeInDays;
        this.minNeighToBeSurrounded = minNeighToBeSurrounded;
        this.maxInfectionDistance = maxInfectionDistance;
    }

}
