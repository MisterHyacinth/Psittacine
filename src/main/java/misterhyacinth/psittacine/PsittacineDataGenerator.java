package misterhyacinth.psittacine;

import misterhyacinth.psittacine.datagen.PsittacineLootTableProvider;
import misterhyacinth.psittacine.datagen.PsittacineModelProvider;
import misterhyacinth.psittacine.datagen.PsittacineRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PsittacineDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(PsittacineModelProvider::new);
		pack.addProvider(PsittacineRecipeProvider::new);
		pack.addProvider(PsittacineLootTableProvider::new);
	}
}
