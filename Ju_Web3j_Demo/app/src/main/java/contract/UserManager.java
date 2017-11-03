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
public final class UserManager extends Contract {
    private static final String BINARY = "";

    private UserManager(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private UserManager(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
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

    public Future<Uint256> getAccountState(Utf8String _account) {
        Function function = new Function("getAccountState",
                Arrays.<Type>asList(_account),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByDepartmentIdTree(Utf8String _departmentId, Uint256 _pageNum, Uint256 _pageSize) {
        Function function = new Function("findByDepartmentIdTree",
                Arrays.<Type>asList(_departmentId, _pageNum, _pageSize),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByMobile(Utf8String _mobile) {
        Function function = new Function("findByMobile",
                Arrays.<Type>asList(_mobile),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> userExists(Address _userAddr) {
        Function function = new Function("userExists",
                Arrays.<Type>asList(_userAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getRevision() {
        Function function = new Function("getRevision",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getErrno() {
        Function function = new Function("getErrno",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> checkUserRole(Address _userAddr, Utf8String _roleId) {
        Function function = new Function("checkUserRole",
                Arrays.<Type>asList(_userAddr, _roleId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> updatePasswordStatus(Address _userAddr, Uint256 _status) {
        Function function = new Function("updatePasswordStatus", Arrays.<Type>asList(_userAddr, _status), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> actionUsed(Utf8String _actionId) {
        Function function = new Function("actionUsed",
                Arrays.<Type>asList(_actionId),
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

    public Future<TransactionReceipt> login(Utf8String _account) {
        Function function = new Function("login", Arrays.<Type>asList(_account), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Int256 _i) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _i),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> pageByAccountStatus(Uint256 _accountStatus, Uint256 _pageNo, Uint256 _pageSize) {
        Function function = new Function("pageByAccountStatus",
                Arrays.<Type>asList(_accountStatus, _pageNo, _pageSize),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> update(Utf8String _userJson) {
        Function function = new Function("update", Arrays.<Type>asList(_userJson), Collections.<TypeReference<?>>emptyList());
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

    public Future<Uint256> getUserState(Address _userAddr) {
        Function function = new Function("getUserState",
                Arrays.<Type>asList(_userAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> kill() {
        Function function = new Function("kill", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> checkWritePermission(Address _addr, Utf8String _departmentId) {
        Function function = new Function("checkWritePermission",
                Arrays.<Type>asList(_addr, _departmentId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByEmail(Utf8String _email) {
        Function function = new Function("findByEmail",
                Arrays.<Type>asList(_email),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Utf8String _str2) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _str2),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> checkRoleAction(Utf8String _roleId, Utf8String _actionId) {
        Function function = new Function("checkRoleAction",
                Arrays.<Type>asList(_roleId, _actionId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByLoginName(Utf8String _name) {
        Function function = new Function("findByLoginName",
                Arrays.<Type>asList(_name),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> updateUserStatus(Address _userAddr, Uint256 _status) {
        Function function = new Function("updateUserStatus", Arrays.<Type>asList(_userAddr, _status), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getSender() {
        Function function = new Function("getSender",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getRoleModuleId(Utf8String _roleId) {
        Function function = new Function("getRoleModuleId",
                Arrays.<Type>asList(_roleId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> eraseAdminByAddress(Address _userAddr) {
        Function function = new Function("eraseAdminByAddress", Arrays.<Type>asList(_userAddr), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> getUserCountMappingByRoleIds(Utf8String _roleIds) {
        Function function = new Function("getUserCountMappingByRoleIds",
                Arrays.<Type>asList(_roleIds),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getChildIdByIndex(Utf8String _departmentId, Uint256 _index) {
        Function function = new Function("getChildIdByIndex",
                Arrays.<Type>asList(_departmentId, _index),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> departmentExists(Utf8String _departmentId) {
        Function function = new Function("departmentExists",
                Arrays.<Type>asList(_departmentId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> listAll() {
        Function function = new Function("listAll",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getUserRoleId(Address _userAddr, Uint256 _index) {
        Function function = new Function("getUserRoleId",
                Arrays.<Type>asList(_userAddr, _index),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByRoleId(Utf8String _roleId) {
        Function function = new Function("findByRoleId",
                Arrays.<Type>asList(_roleId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getUserCountByDepartmentId(Utf8String _departmentId) {
        Function function = new Function("getUserCountByDepartmentId",
                Arrays.<Type>asList(_departmentId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
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

    public Future<Uint256> checkActionExists(Utf8String _actionId) {
        Function function = new Function("checkActionExists",
                Arrays.<Type>asList(_actionId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> deleteByAddress(Address _userAddr) {
        Function function = new Function("deleteByAddress", Arrays.<Type>asList(_userAddr), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> addUserRole(Address _userAddr, Utf8String _roleId) {
        Function function = new Function("addUserRole", Arrays.<Type>asList(_userAddr, _roleId), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Utf8String> findByAddress(Address _userAddr) {
        Function function = new Function("findByAddress",
                Arrays.<Type>asList(_userAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByDepartmentIdTreeAndContion(Uint256 _status, Utf8String _name, Utf8String _departmentId, Uint256 _pageNum, Uint256 _pageSize) {
        Function function = new Function("findByDepartmentIdTreeAndContion",
                Arrays.<Type>asList(_status, _name, _departmentId, _pageNum, _pageSize),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByAccount(Utf8String _account) {
        Function function = new Function("findByAccount",
                Arrays.<Type>asList(_account),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> roleUsed(Utf8String _roleId) {
        Function function = new Function("roleUsed",
                Arrays.<Type>asList(_roleId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> actionExists(Utf8String _actionId) {
        Function function = new Function("actionExists",
                Arrays.<Type>asList(_actionId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getUserDepartmentId(Address _userAddr) {
        Function function = new Function("getUserDepartmentId",
                Arrays.<Type>asList(_userAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> checkUserAction(Address _userAddr, Utf8String _actionId) {
        Function function = new Function("checkUserAction",
                Arrays.<Type>asList(_userAddr, _actionId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getUserAddrByAddr(Address _userAddr) {
        Function function = new Function("getUserAddrByAddr",
                Arrays.<Type>asList(_userAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> insert(Utf8String _userJson) {
        Function function = new Function("insert", Arrays.<Type>asList(_userJson), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> getUserCount() {
        Function function = new Function("getUserCount",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> log(Utf8String _str, Uint256 _ui) {
        Function function = new Function("log",
                Arrays.<Type>asList(_str, _ui),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Address> getOwnerAddrByAddr(Address _userAddr) {
        Function function = new Function("getOwnerAddrByAddr",
                Arrays.<Type>asList(_userAddr),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getUserCountByActionId(Utf8String _actionId) {
        Function function = new Function("getUserCountByActionId",
                Arrays.<Type>asList(_actionId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> resetPasswd(Address _userAddr, Address _ownerAddr, Utf8String _publilcKey, Utf8String _cipherGroupKey, Utf8String _uuid) {
        Function function = new Function("resetPasswd", Arrays.<Type>asList(_userAddr, _ownerAddr, _publilcKey, _cipherGroupKey, _uuid), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Uint256> roleExists(Utf8String _roleId) {
        Function function = new Function("roleExists",
                Arrays.<Type>asList(_roleId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByDepartmentId(Utf8String _departmentId) {
        Function function = new Function("findByDepartmentId",
                Arrays.<Type>asList(_departmentId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Utf8String> findByUuid(Utf8String _uuid) {
        Function function = new Function("findByUuid",
                Arrays.<Type>asList(_uuid),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> checkUserPrivilege(Address _userAddr, Address _contractAddr, Utf8String _funcSha3) {
        Function function = new Function("checkUserPrivilege",
                Arrays.<Type>asList(_userAddr, _contractAddr, _funcSha3),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getAdmin(Utf8String _departmentId) {
        Function function = new Function("getAdmin",
                Arrays.<Type>asList(_departmentId),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> getRoleIdByActionIdAndIndex(Utf8String _actionId, Uint256 _index) {
        Function function = new Function("getRoleIdByActionIdAndIndex",
                Arrays.<Type>asList(_actionId, _index),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Uint256> checkActionWithKey(Utf8String _actionId, Address _resKey, Utf8String _opSha3Key) {
        Function function = new Function("checkActionWithKey",
                Arrays.<Type>asList(_actionId, _resKey, _opSha3Key),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> updateAccountStatus(Address _userAddr, Uint256 _status) {
        Function function = new Function("updateAccountStatus", Arrays.<Type>asList(_userAddr, _status), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<UserManager> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(UserManager.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static Future<UserManager> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialValue) {
        return deployAsync(UserManager.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialValue);
    }

    public static UserManager load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static UserManager load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UserManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class NotifyEventResponse {
        public Uint256 _errno;

        public Utf8String _info;
    }
}
