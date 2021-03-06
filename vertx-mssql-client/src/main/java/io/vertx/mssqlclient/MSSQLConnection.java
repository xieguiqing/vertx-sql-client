package io.vertx.mssqlclient;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.mssqlclient.impl.MSSQLConnectionImpl;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.sqlclient.PreparedQuery;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlConnection;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.Tuple;

import java.util.List;
import java.util.stream.Collector;

/**
 * A connection to Microsoft SQL Server.
 */
@VertxGen
public interface MSSQLConnection extends SqlConnection {

  /**
   * Create a connection to SQL Server with the given {@code connectOptions}.
   *
   * @param vertx          the vertx instance
   * @param connectOptions the options for the connection
   * @param handler        the handler called with the connection or the failure
   */
  static void connect(Vertx vertx, MSSQLConnectOptions connectOptions, Handler<AsyncResult<MSSQLConnection>> handler) {
    Future<MSSQLConnection> fut = MSSQLConnectionImpl.connect(vertx, connectOptions);
    if (handler != null) {
      fut.setHandler(handler);
    }
  }

  /**
   * Like {@link #connect(Vertx, MSSQLConnectOptions, Handler)} but returns a {@code Future} of the asynchronous result
   */
  static Future<MSSQLConnection> connect(Vertx vertx, MSSQLConnectOptions connectOptions) {
    return MSSQLConnectionImpl.connect(vertx, connectOptions);
  }

  @Override
  MSSQLConnection prepare(String s, Handler<AsyncResult<PreparedQuery>> handler);

  @Override
  MSSQLConnection exceptionHandler(Handler<Throwable> handler);

  @Override
  MSSQLConnection closeHandler(Handler<Void> handler);

  @Override
  MSSQLConnection preparedQuery(String s, Handler<AsyncResult<RowSet<Row>>> handler);

  @GenIgnore
  @Override
  <R> MSSQLConnection preparedQuery(String s, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler);

  @Override
  MSSQLConnection query(String s, Handler<AsyncResult<RowSet<Row>>> handler);

  @GenIgnore
  @Override
  <R> MSSQLConnection query(String s, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler);

  @Override
  MSSQLConnection preparedQuery(String s, Tuple tuple, Handler<AsyncResult<RowSet<Row>>> handler);

  @GenIgnore
  @Override
  <R> MSSQLConnection preparedQuery(String s, Tuple tuple, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler);

  @Override
  MSSQLConnection preparedBatch(String s, List<Tuple> list, Handler<AsyncResult<RowSet<Row>>> handler);

  @GenIgnore
  @Override
  <R> MSSQLConnection preparedBatch(String s, List<Tuple> list, Collector<Row, ?, R> collector, Handler<AsyncResult<SqlResult<R>>> handler);
}
