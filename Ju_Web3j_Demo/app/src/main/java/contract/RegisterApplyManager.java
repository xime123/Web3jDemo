package contract;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.1.0.
 */
public final class RegisterApplyManager extends Contract {
    private static final String BINARY = "";

    private RegisterApplyManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private RegisterApplyManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<NotifyEventResponse> getNotifyEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Notify",
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        List<EventValues> valueList = extractEventParameters(event,transactionReceipt);
        ArrayList<NotifyEventResponse> responses = new ArrayList<NotifyEventResponse>(valueList.size());
        for(EventValues eventValues : valueList) {
            NotifyEventResponse typedResponse = new NotifyEventResponse();
            typedResponse._errno = (Uint256)eventValues.getNonIndexedValues().get(0);
            typedResponse._info = (Utf8String)eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NotifyEventResponse> notifyEventObservable() {
        final Event event = new Event("Notify",
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NotifyEventResponse>() {
            @Override
            public NotifyEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NotifyEventResponse typedResponse = new NotifyEventResponse();
                typedResponse._errno = (Uint256)eventValues.getNonIndexedValues().get(0);
                typedResponse._info = (Utf8String)eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Future<Uint256> getErrno() {
        Function function = new Function("getErrno",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Utf8String _str2, Utf8String _str3) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _str2, _str3),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Address _addr) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _addr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Int256 _i) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _i),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> update(Utf8String _json) {
        Function function = new Function("update", Arrays.<Type>asList(_json), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> register(Utf8String _name, Utf8String _version) {
        Function function = new Function("register", Arrays.<Type>asList(_name, _version), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> log(Utf8String _str) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Utf8String _str2) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _str2),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findById(Utf8String _applyId) {
        Function function = new Function("findById",
                Arrays.<Type>asList(_applyId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getSender() {
        Function function = new Function("getSender",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> listAll() {
        Function function = new Function("listAll",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> audit(Utf8String _json) {
        Function function = new Function("audit", Arrays.<Type>asList(_json), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> writedb(Utf8String _name, Utf8String _key, Utf8String _value) {
        Function function = new Function("writedb",
                Arrays.<Type>asList(_name, _key, _value),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getOwner() {
        Function function = new Function("getOwner",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> insert(Utf8String _json) {
        Function function = new Function("insert", Arrays.<Type>asList(_json), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Uint256 _ui) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _ui),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByUuid(Utf8String _uuid) {
        Function function = new Function("findByUuid",
                Arrays.<Type>asList(_uuid),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> listByCondition(Utf8String _name, Utf8String _mobile, Uint256 _certType, Uint256 _pageSize, Uint256 _pageNo, Utf8String _auditStatus, Uint256 _accountStatus) {
        Function function = new Function("listByCondition",
                Arrays.<Type>asList(_name, _mobile, _certType, _pageSize, _pageNo, _auditStatus, _accountStatus),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<RegisterApplyManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(RegisterApplyManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<RegisterApplyManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(RegisterApplyManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static RegisterApplyManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RegisterApplyManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static RegisterApplyManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RegisterApplyManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class NotifyEventResponse {
        public Uint256 _errno;

        public Utf8String _info;
    }
}
