package AES

import chisel3._

class SR_IO extends Bundle{
  val data_in = Input(UInt(128.W))
  val data_out = Output(UInt(128.W))
}

class XOR_IO(width:Int) extends Bundle{
  val data_in_1 = Input(UInt(width.W))
  val data_in_2 = Input(UInt(width.W))
  val data_out = Output(UInt(width.W))

  override def cloneType: XOR_IO.this.type = (new XOR_IO(width)).asInstanceOf[this.type]
}

class BS_MC_IO extends Bundle{
  val data_in = Input(UInt(128.W))
  val data_out = Output(UInt(128.W))
}

class AES128_IO extends Bundle{
  val data_in = Input(UInt(128.W))
  val key_in = Input(UInt(128.W))
  val data_out = Output(UInt(128.W))
}

class G_IO extends Bundle{
  val data_in = Input(UInt(32.W))
  val rc_in = Input(UInt(8.W))
  val data_out = Output(UInt(32.W))
}

class INV_KEY_GEN_IO extends Bundle{
  val key_in = Input(UInt(128.W))
  val key_out = Output(Vec(11,UInt(128.W)))
}

class INV_MC_IO extends Bundle{
  val data_in = Input(UInt(128.W))
  val data_out = Output(UInt(128.W))
}

class GFMULT_IO extends Bundle{
  val data_in_1 = Input(UInt(8.W))
  val data_in_2 = Input(UInt(8.W))
  val data_out = Output(UInt(8.W))
}