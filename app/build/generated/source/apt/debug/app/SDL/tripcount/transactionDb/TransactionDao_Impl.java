package app.SDL.tripcount.transactionDb;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfTransactionEntry;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTransactionEntry;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfTransactionEntry;

  public TransactionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntry = new EntityInsertionAdapter<TransactionEntry>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `transactionTable`(`id`,`amount`,`category`,`description`,`date`,`transactionType`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getAmount());
        if (value.getCategory() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCategory());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        final Long _tmp;
        _tmp = DateConvertor.toTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp);
        }
        if (value.getTransactionType() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTransactionType());
        }
      }
    };
    this.__deletionAdapterOfTransactionEntry = new EntityDeletionOrUpdateAdapter<TransactionEntry>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `transactionTable` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntry value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTransactionEntry = new EntityDeletionOrUpdateAdapter<TransactionEntry>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `transactionTable` SET `id` = ?,`amount` = ?,`category` = ?,`description` = ?,`date` = ?,`transactionType` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntry value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getAmount());
        if (value.getCategory() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCategory());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        final Long _tmp;
        _tmp = DateConvertor.toTimestamp(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, _tmp);
        }
        if (value.getTransactionType() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTransactionType());
        }
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  public void insertExpense(TransactionEntry transactionEntry) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransactionEntry.insert(transactionEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void removeExpense(TransactionEntry transactionEntry) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfTransactionEntry.handle(transactionEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateExpenseDetails(TransactionEntry transactionEntry) {
    __db.beginTransaction();
    try {
      __updateAdapterOfTransactionEntry.handle(transactionEntry);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<TransactionEntry>> loadAllTransactions() {
    final String _sql = "select * from transactionTable order by date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<TransactionEntry>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<TransactionEntry> compute() {
        if (_observer == null) {
          _observer = new Observer("transactionTable") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final List<TransactionEntry> _result = new ArrayList<TransactionEntry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TransactionEntry _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateConvertor.toDate(_tmp);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _item = new TransactionEntry(_tmpId,_tmpAmount,_tmpCategory,_tmpDescription,_tmpDate,_tmpTransactionType);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<TransactionEntry> loadExpenseById(int id) {
    final String _sql = "select * from transactionTable where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return new ComputableLiveData<TransactionEntry>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected TransactionEntry compute() {
        if (_observer == null) {
          _observer = new Observer("transactionTable") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfAmount = _cursor.getColumnIndexOrThrow("amount");
          final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
          final int _cursorIndexOfTransactionType = _cursor.getColumnIndexOrThrow("transactionType");
          final TransactionEntry _result;
          if(_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpAmount;
            _tmpAmount = _cursor.getInt(_cursorIndexOfAmount);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Date _tmpDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDate);
            }
            _tmpDate = DateConvertor.toDate(_tmp);
            final String _tmpTransactionType;
            _tmpTransactionType = _cursor.getString(_cursorIndexOfTransactionType);
            _result = new TransactionEntry(_tmpId,_tmpAmount,_tmpCategory,_tmpDescription,_tmpDate,_tmpTransactionType);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public int getAmountByTransactionType(String transactionType) {
    final String _sql = "select sum(amount) from transactionTable where transactionType =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (transactionType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, transactionType);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAmountbyCustomDates(String transactionType, long startDate, long endDate) {
    final String _sql = "select sum(amount) from transactionTable where transactionType =? and  date between ? and ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (transactionType == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, transactionType);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getSumExpenseByCategory(String category) {
    final String _sql = "select sum(amount) from transactionTable where category=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getSumExpenseByCategoryCustomDate(String category, long startDate, long endDate) {
    final String _sql = "select sum(amount) from transactionTable where category=? and date between ? and ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public long getFirstDate() {
    final String _sql = "select min(date) from transactionTable ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final long _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getLong(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
