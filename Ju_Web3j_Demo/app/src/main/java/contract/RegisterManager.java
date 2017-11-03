package contract;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.1.0.
 */
public final class RegisterManager extends Contract {
    private static final String BINARY = "";

    private RegisterManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private RegisterManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public Future<TransactionReceipt> register(Utf8String _moduleName, Utf8String _moduleVersion, Utf8String _contractName, Utf8String _contractVersion) {
        Function function = new Function("register", Arrays.<Type>asList(_moduleName, _moduleVersion, _contractName, _contractVersion), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> findModuleNameByAddress(Address _addr) {
        Function function = new Function("findModuleNameByAddress",
                Arrays.<Type>asList(_addr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> IfContractRegist(Address _contractAddr) {
        Function function = new Function("IfContractRegist",
                Arrays.<Type>asList(_contractAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> unRegister() {
        Function function = new Function("unRegister", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> IfModuleRegist(Utf8String _moduleName, Utf8String _moduleVersion) {
        Function function = new Function("IfModuleRegist",
                Arrays.<Type>asList(_moduleName, _moduleVersion),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getModuleAddress(Utf8String _moduleName, Utf8String _moduleVersion) {
        Function function = new Function("getModuleAddress",
                Arrays.<Type>asList(_moduleName, _moduleVersion),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> transferContract(Utf8String _fromModuleNameAndVersion, Utf8String _fromNameAndVersion, Utf8String _toModuleNameAndVersion, Utf8String _toNameAndVersion, Utf8String _signString) {
        Function function = new Function("transferContract", Arrays.<Type>asList(_fromModuleNameAndVersion, _fromNameAndVersion, _toModuleNameAndVersion, _toNameAndVersion, _signString), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bool> IfContractRegist(Utf8String _moduleName, Utf8String _moduleVersion, Utf8String _contractName, Utf8String _contractVersion) {
        Function function = new Function("IfContractRegist",
                Arrays.<Type>asList(_moduleName, _moduleVersion, _contractName, _contractVersion),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> changeModuleRegisterOwner(Utf8String _moduleName, Utf8String _moduleVersion, Address _newOwner) {
        Function function = new Function("changeModuleRegisterOwner", Arrays.<Type>asList(_moduleName, _moduleVersion, _newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> register(Utf8String _moduleName, Utf8String _moduleVersion) {
        Function function = new Function("register", Arrays.<Type>asList(_moduleName, _moduleVersion), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> findContractVersionByAddress(Address _addr) {
        Function function = new Function("findContractVersionByAddress",
                Arrays.<Type>asList(_addr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> findResNameByAddress(Address _addr) {
        Function function = new Function("findResNameByAddress",
                Arrays.<Type>asList(_addr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> changeContractRegisterOwner(Utf8String _moduleName, Utf8String _moduleVersion, Utf8String _contractName, Utf8String _contractVersion, Address _newOwner) {
        Function function = new Function("changeContractRegisterOwner", Arrays.<Type>asList(_moduleName, _moduleVersion, _contractName, _contractVersion, _newOwner), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Address> getContractAddress(Utf8String _moduleName, Utf8String _moduleVersion, Utf8String _contractName, Utf8String _contractVersion) {
        Function function = new Function("getContractAddress",
                Arrays.<Type>asList(_moduleName, _moduleVersion, _contractName, _contractVersion),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> findModuleVersionByAddress(Address _addr) {
        Function function = new Function("findModuleVersionByAddress",
                Arrays.<Type>asList(_addr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> getRegisteredContract(Uint256 _pageNum, Uint256 _pageSize) {
        Function function = new Function("getRegisteredContract",
                Arrays.<Type>asList(_pageNum, _pageSize),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> IfModuleRegist(Address _moduleAddr) {
        Function function = new Function("IfModuleRegist",
                Arrays.<Type>asList(_moduleAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public static Future<RegisterManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(RegisterManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<RegisterManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(RegisterManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static RegisterManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new RegisterManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static RegisterManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RegisterManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
