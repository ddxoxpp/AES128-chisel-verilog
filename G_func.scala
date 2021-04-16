package AES

import chisel3._
import chisel3.util._

class G_func extends RawModule{
  val io = IO(new G_IO)
  val s_table = VecInit(S_table.s)
  io.data_out := Cat(s_table(io.data_in(7,0)),s_table(io.data_in(31,24)),s_table(io.data_in(23,16)),XOR8(s_table(io.data_in(15,8)),io.rc_in))
}

object G_func{
  def apply(data_in:UInt , rc:UInt) = {
    val g = Module( new G_func)
    g.io.data_in := data_in
    g.io.rc_in := rc
    g.io.data_out
  }
}
