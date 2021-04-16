package AES

import chisel3._
import chisel3.util._

class Inv_key_MC extends RawModule {
  val io = IO(new INV_MC_IO)
  val k0 = Wire(UInt(8.W))
  val k1 = Wire(UInt(8.W))
  val k2 = Wire(UInt(8.W))
  val k3 = Wire(UInt(8.W))
  val k4 = Wire(UInt(8.W))
  val k5 = Wire(UInt(8.W))
  val k6 = Wire(UInt(8.W))
  val k7 = Wire(UInt(8.W))
  val k8 = Wire(UInt(8.W))
  val k9 = Wire(UInt(8.W))
  val k10 = Wire(UInt(8.W))
  val k11 = Wire(UInt(8.W))
  val k12 = Wire(UInt(8.W))
  val k13 = Wire(UInt(8.W))
  val k14 = Wire(UInt(8.W))
  val k15 = Wire(UInt(8.W))
  k0 := ((GFMult(0x0E.U , io.data_in(7,0)))^(GFMult(0x0B.U , io.data_in(15,8)))^(GFMult(0x0D.U , io.data_in(23,16)))^(GFMult(0x09.U , io.data_in(31,24))))(7,0)
  k1 := ((GFMult(0x09.U , io.data_in(7,0)))^(GFMult(0x0E.U , io.data_in(15,8)))^(GFMult(0x0B.U , io.data_in(23,16)))^(GFMult(0x0D.U , io.data_in(31,24))))(7,0)
  k2 := ((GFMult(0x0D.U , io.data_in(7,0)))^(GFMult(0x09.U , io.data_in(15,8)))^(GFMult(0x0E.U , io.data_in(23,16)))^(GFMult(0x0B.U , io.data_in(31,24))))(7,0)
  k3 := ((GFMult(0x0B.U , io.data_in(7,0)))^(GFMult(0x0D.U , io.data_in(15,8)))^(GFMult(0x09.U , io.data_in(23,16)))^(GFMult(0x0E.U , io.data_in(31,24))))(7,0)

  k4 := ((GFMult(0x0E.U , io.data_in(39,32)))^(GFMult(0x0B.U , io.data_in(47,40)))^(GFMult(0x0D.U , io.data_in(55,48)))^(GFMult(0x09.U , io.data_in(63,56))))(7,0)
  k5 := ((GFMult(0x09.U , io.data_in(39,32)))^(GFMult(0x0E.U , io.data_in(47,40)))^(GFMult(0x0B.U , io.data_in(55,48)))^(GFMult(0x0D.U , io.data_in(63,56))))(7,0)
  k6 := ((GFMult(0x0D.U , io.data_in(39,32)))^(GFMult(0x09.U , io.data_in(47,40)))^(GFMult(0x0E.U , io.data_in(55,48)))^(GFMult(0x0B.U , io.data_in(63,56))))(7,0)
  k7 := ((GFMult(0x0B.U , io.data_in(39,32)))^(GFMult(0x0D.U , io.data_in(47,40)))^(GFMult(0x09.U , io.data_in(55,48)))^(GFMult(0x0E.U , io.data_in(63,56))))(7,0)

  k8  := ((GFMult(0x0E.U , io.data_in(71,64)))^(GFMult(0x0B.U , io.data_in(79,72)))^(GFMult(0x0D.U , io.data_in(87,80)))^(GFMult(0x09.U , io.data_in(95,88))))(7,0)
  k9  := ((GFMult(0x09.U , io.data_in(71,64)))^(GFMult(0x0E.U , io.data_in(79,72)))^(GFMult(0x0B.U , io.data_in(87,80)))^(GFMult(0x0D.U , io.data_in(95,88))))(7,0)
  k10 := ((GFMult(0x0D.U , io.data_in(71,64)))^(GFMult(0x09.U , io.data_in(79,72)))^(GFMult(0x0E.U , io.data_in(87,80)))^(GFMult(0x0B.U , io.data_in(95,88))))(7,0)
  k11 := ((GFMult(0x0B.U , io.data_in(71,64)))^(GFMult(0x0D.U , io.data_in(79,72)))^(GFMult(0x09.U , io.data_in(87,80)))^(GFMult(0x0E.U , io.data_in(95,88))))(7,0)

  k12 := ((GFMult(0x0E.U , io.data_in(103,96)))^(GFMult(0x0B.U , io.data_in(111,104)))^(GFMult(0x0D.U , io.data_in(119,112)))^(GFMult(0x09.U , io.data_in(127,120))))(7,0)
  k13 := ((GFMult(0x09.U , io.data_in(103,96)))^(GFMult(0x0E.U , io.data_in(111,104)))^(GFMult(0x0B.U , io.data_in(119,112)))^(GFMult(0x0D.U , io.data_in(127,120))))(7,0)
  k14 := ((GFMult(0x0D.U , io.data_in(103,96)))^(GFMult(0x09.U , io.data_in(111,104)))^(GFMult(0x0E.U , io.data_in(119,112)))^(GFMult(0x0B.U , io.data_in(127,120))))(7,0)
  k15 := ((GFMult(0x0B.U , io.data_in(103,96)))^(GFMult(0x0D.U , io.data_in(111,104)))^(GFMult(0x09.U , io.data_in(119,112)))^(GFMult(0x0E.U , io.data_in(127,120))))(7,0)

  io.data_out := Cat(k15,k14,k13,k12,k11,k10,k9,k8,k7,k6,k5,k4,k3,k2,k1,k0)
}

object Inv_key_MC{
  def apply(data_in:UInt) = {
    val m = Module(new Inv_key_MC)
    m.io.data_in := data_in
    m.io.data_out
  }
}
