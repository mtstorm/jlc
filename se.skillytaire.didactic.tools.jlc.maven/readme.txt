Reason


In order to use the service providers in JUnit and in JLC you need to specify
these in the module-info.java . Since most build IDE doe not support that, having
two module-info.java files, we have a problem.

In order to work with the full stack, so also the test cycle, we need to have a module-info
in the test source. Since that module file has more declared we need to figure it out what 
you need only for compile time. The ide is as following

hava a module-info.txt file somewhere you can use in your IDE and add your stuff in there for your compile cycle




<plugin>

	
	<configuration>
		<moduleentries>
			<!-- all listed modules are required -->
			<module>
				<name>java.logging</name> <!-- name or dependency -->
				<transitive>false</transitive>
				<static>false</static>
			</module>		
			<module>
				<dependency>
		        	<groupId>org.junit.jupiter</groupId>
		        	<artifactId>junit-jupiter-api</artifactId>
					<scope>test</scope>
				</dependency>
				<opens>true/false</opens>  <!-- opens transitivaly -->
				<uses>
					<spi>org.junit.jupiter.api.extension.Extension</spi>
				</uses>
			</module>		
			<module>
				<dependency>
					<groupId>se.skillytaire.didactic.tools.jlc</groupId>
					<artifactId>core</artifactId>
					<scope>test</scope>
				</dependency>
				<required>true/false</required> <!-- default is true -->
				<opens>true/false</opens>
				<uses>
					<spi>se.skillytaire.didactic.tools.jlc.api.TestObjectFactory</spi>
				</uses>
			</module>
		</moduleentries>
		<providers>
			<provider>
				<interface></interface>
				<implementations>
					<implementation></implementation>
					<implementation></implementation>
				</implementations>
				<used>true/false</used> <!-- when also used internally = true default is false -->
			</provider>
		</providers>
		
	</configuration>

</plugin> 