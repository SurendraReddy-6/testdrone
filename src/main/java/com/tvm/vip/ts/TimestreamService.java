package com.tvm.vip.ts;

import java.util.ArrayList;
import java.util.List;

import com.tvm.vip.entity.TVMModules;
import com.tvm.vip.entity.TVMSales;
import com.tvm.vip.entity.TVMStatus;
import com.tvm.vip.utils.AWSTimestreamUtils;

import software.amazon.awssdk.services.timestreamwrite.TimestreamWriteClient;
import software.amazon.awssdk.services.timestreamwrite.model.ConflictException;
import software.amazon.awssdk.services.timestreamwrite.model.CreateDatabaseRequest;
import software.amazon.awssdk.services.timestreamwrite.model.CreateTableRequest;
import software.amazon.awssdk.services.timestreamwrite.model.Database;
import software.amazon.awssdk.services.timestreamwrite.model.DeleteTableRequest;
import software.amazon.awssdk.services.timestreamwrite.model.DeleteTableResponse;
import software.amazon.awssdk.services.timestreamwrite.model.Dimension;
import software.amazon.awssdk.services.timestreamwrite.model.ListDatabasesRequest;
import software.amazon.awssdk.services.timestreamwrite.model.ListDatabasesResponse;
import software.amazon.awssdk.services.timestreamwrite.model.MeasureValueType;
import software.amazon.awssdk.services.timestreamwrite.model.RejectedRecordsException;
import software.amazon.awssdk.services.timestreamwrite.model.ResourceNotFoundException;
import software.amazon.awssdk.services.timestreamwrite.model.RetentionProperties;
import software.amazon.awssdk.services.timestreamwrite.model.WriteRecordsRequest;
import software.amazon.awssdk.services.timestreamwrite.model.WriteRecordsResponse;
import software.amazon.awssdk.services.timestreamwrite.paginators.ListDatabasesIterable;
import software.amazon.awssdk.services.timestreamwrite.model.Record;

public class TimestreamService {

	public static final long HT_TTL_HOURS = 24L;
	public static final long CT_TTL_DAYS = 7L;

	TimestreamWriteClient timestreamWriteClient;

	public TimestreamService(TimestreamWriteClient client) {
		this.timestreamWriteClient = client;
	}

	public void createDatabase(String database) {
		System.out.println("Creating database");
		CreateDatabaseRequest request = CreateDatabaseRequest.builder().databaseName(database).build();
		try {
			timestreamWriteClient.createDatabase(request);
			System.out.println("Database [" + database + "] created successfully");
		} catch (ConflictException e) {
			System.out.println("Database [" + database + "] exists. Skipping database creation");
		}
	}

	public void listDatabases() {
		System.out.println("Listing databases");
		ListDatabasesRequest request = ListDatabasesRequest.builder().maxResults(2).build();
		ListDatabasesIterable listDatabasesIterable = timestreamWriteClient.listDatabasesPaginator(request);
		for (ListDatabasesResponse listDatabasesResponse : listDatabasesIterable) {
			final List<Database> databases = listDatabasesResponse.databases();
			databases.forEach(database -> System.out.println(database.databaseName()));
		}
	}

	public void createTable(String database, String tableName) {
		System.out.println("Creating table");

		final RetentionProperties retentionProperties = RetentionProperties.builder()
				.memoryStoreRetentionPeriodInHours(HT_TTL_HOURS).magneticStoreRetentionPeriodInDays(CT_TTL_DAYS)
				.build();
		final CreateTableRequest createTableRequest = CreateTableRequest.builder().databaseName(database)
				.tableName(tableName).retentionProperties(retentionProperties).build();

		try {
			timestreamWriteClient.createTable(createTableRequest);
			System.out.println("Table [" + tableName + "] successfully created.");
		} catch (ConflictException e) {
			System.out.println(
					"Table [" + tableName + "] exists on database [" + database + "] . Skipping database creation");
		}
	}

	public void deleteTable(String database, String tableName) {
		System.out.println("Deleting table");
		final DeleteTableRequest deleteTableRequest = DeleteTableRequest.builder().databaseName(database)
				.tableName(tableName).build();
		try {
			DeleteTableResponse response = timestreamWriteClient.deleteTable(deleteTableRequest);
			System.out.println("Delete table status: " + response.sdkHttpResponse().statusCode());
		} catch (final ResourceNotFoundException e) {
			System.out.println("Table " + tableName + " doesn't exist = " + e);
			throw e;
		} catch (final Exception e) {
			System.out.println("Could not delete table " + tableName + " = " + e);
			throw e;
		}
	}

	public void writeRecordsSales(TVMSales sales) {
		System.out.println("Writing records");
		// Specify repeated values for all records
		List<Record> records = new ArrayList<>();
		final long time = System.currentTimeMillis();

		List<Dimension> dimensions = new ArrayList<>();
		// final Dimension id =
		// Dimension.builder().name("id").value(sales.getId().toString()).build();
		final Dimension amount = Dimension.builder().name("amount").value(sales.getAmount().toString()).build();
		final Dimension transcation_id = Dimension.builder().name("transcation_id")
				.value(sales.getTransaction_id().toString()).build();

		final Dimension productCode = Dimension.builder().name("productCode").value(sales.getProductCode().toString())
				.build();
		final Dimension ticketid = Dimension.builder().name("ticketid").value(sales.getTicketid()).build();
		final Dimension productType = Dimension.builder().name("productType").value(sales.getProductType()).build();

		final Dimension cashAmount = Dimension.builder().name("cashAmount").value(sales.getCashAmount().toString())
				.build();
		final Dimension changeAmount = Dimension.builder().name("changeAmount")
				.value(sales.getChangeAmount().toString()).build();
		final Dimension ticketAmount = Dimension.builder().name("ticketAmount")
				.value(sales.getTicketAmount().toString()).build();

		final Dimension createdon = Dimension.builder().name("createdon").value(sales.getCreatedon().toString())
				.build();
		final Dimension tvmid = Dimension.builder().name("tvmid").value(sales.getTvmid().toString()).build();
		final Dimension payment_method = Dimension.builder().name("payment_method").value(sales.getPayment_method())
				.build();

//        dimensions.add(id);
		dimensions.add(amount);
		dimensions.add(transcation_id);
		dimensions.add(productCode);
		dimensions.add(ticketid);
		dimensions.add(productType);
		dimensions.add(cashAmount);
		dimensions.add(changeAmount);
		dimensions.add(ticketAmount);
		dimensions.add(createdon);
		dimensions.add(tvmid);
		dimensions.add(payment_method);

		Record cpuUtilization = Record.builder().dimensions(dimensions).measureValueType(MeasureValueType.DOUBLE)
				.measureName("cpu_utilization").measureValue("18").time(String.valueOf(time)).build();

		Record memoryUtilization = Record.builder().dimensions(dimensions).measureValueType(MeasureValueType.DOUBLE)
				.measureName("memory_utilization").measureValue("23").time(String.valueOf(time)).build();

		records.add(cpuUtilization);
		records.add(memoryUtilization);

		WriteRecordsRequest writeRecordsRequest = WriteRecordsRequest.builder()
				.databaseName(AWSTimestreamUtils.DATABASE_NAME).tableName(AWSTimestreamUtils.SALES_TABLE)
				.records(records).build();

		try {
			WriteRecordsResponse writeRecordsResponse = timestreamWriteClient.writeRecords(writeRecordsRequest);
			System.out.println("WriteRecords Status: " + writeRecordsResponse.sdkHttpResponse().statusCode());
		} catch (RejectedRecordsException e) {
			printRejectedRecordsException(e);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public void writeRecordsStatus(TVMStatus status, List<TVMModules> moduleList) {
		System.out.println("Writing records");

		addStatus(status);
		addModules(moduleList);
	}

	private void addModules(List<TVMModules> moduleList) {

		for (TVMModules module : moduleList) {
			List<Record> records = new ArrayList<>();
			List<Dimension> dimensions = new ArrayList<>();
			final Dimension name = Dimension.builder().name("name").value(module.getName()).build();
			final Dimension status_code = Dimension.builder().name("status_code")
					.value(module.getStatus_code().toString()).build();

			final Dimension module_serial = Dimension.builder().name("module_serial").value(module.getModule_serial())
					.build();
			final Dimension tvmid = Dimension.builder().name("tvmid").value(module.getTvmid().toString()).build();
			final Dimension value = Dimension.builder().name("value").value(module.getValue().toString()).build();

			dimensions.add(name);
			dimensions.add(status_code);
			dimensions.add(module_serial);
			dimensions.add(tvmid);
			dimensions.add(value);

			Record cpuUtilization = Record.builder().dimensions(dimensions).measureValueType(MeasureValueType.DOUBLE)
					.measureName("cpu_utilization").measureValue("18").time(String.valueOf(System.currentTimeMillis()))
					.build();

			Record memoryUtilization = Record.builder().dimensions(dimensions).measureValueType(MeasureValueType.DOUBLE)
					.measureName("memory_utilization").measureValue("23")
					.time(String.valueOf(System.currentTimeMillis())).build();

			records.add(cpuUtilization);
			records.add(memoryUtilization);

			WriteRecordsRequest writeRecordsRequest = WriteRecordsRequest.builder()
					.databaseName(AWSTimestreamUtils.DATABASE_NAME).tableName(AWSTimestreamUtils.MODULE_TABLE)
					.records(records).build();
			try {
				WriteRecordsResponse writeRecordsResponse = timestreamWriteClient.writeRecords(writeRecordsRequest);
				System.out.println("WriteRecords Status: " + writeRecordsResponse.sdkHttpResponse().statusCode());
			} catch (RejectedRecordsException e) {
				printRejectedRecordsException(e);
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}

		}

	}

	private void addStatus(TVMStatus status) {

		List<Record> records = new ArrayList<>();
		List<Dimension> dimensions = new ArrayList<>();

		// final Dimension id =
		// Dimension.builder().name("id").value(status.getId().toString()).build();
		final Dimension name = Dimension.builder().name("name").value(status.getName()).build();
		final Dimension status_code = Dimension.builder().name("status_code").value(status.getStatus_code().toString())
				.build();

		final Dimension value = Dimension.builder().name("value").value(status.getValue()).build();
		final Dimension tvmid = Dimension.builder().name("tvmid").value(status.getTvmid().toString()).build();

		// dimensions.add(id);
		dimensions.add(name);
		dimensions.add(status_code);
		dimensions.add(value);
		dimensions.add(tvmid);

		Record cpuUtilization = Record.builder().dimensions(dimensions).measureValueType(MeasureValueType.DOUBLE)
				.measureName("cpu_utilization").measureValue("18").time(String.valueOf(System.currentTimeMillis()))
				.build();

		Record memoryUtilization = Record.builder().dimensions(dimensions).measureValueType(MeasureValueType.DOUBLE)
				.measureName("memory_utilization").measureValue("23").time(String.valueOf(System.currentTimeMillis()))
				.build();

		records.add(cpuUtilization);
		records.add(memoryUtilization);

		WriteRecordsRequest writeRecordsRequest = WriteRecordsRequest.builder()
				.databaseName(AWSTimestreamUtils.DATABASE_NAME).tableName(AWSTimestreamUtils.STATUS_TABLE)
				.records(records).build();
		try {
			WriteRecordsResponse writeRecordsResponse = timestreamWriteClient.writeRecords(writeRecordsRequest);
			System.out.println("WriteRecords Status: " + writeRecordsResponse.sdkHttpResponse().statusCode());
		} catch (RejectedRecordsException e) {
			printRejectedRecordsException(e);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

	private void printRejectedRecordsException(RejectedRecordsException e) {
		System.out.println("RejectedRecords: " + e);
		e.rejectedRecords().forEach(System.out::println);
	}

}
