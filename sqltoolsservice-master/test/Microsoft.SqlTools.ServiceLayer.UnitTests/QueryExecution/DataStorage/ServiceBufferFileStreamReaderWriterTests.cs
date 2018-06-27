﻿//
// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.
//

using System;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.Diagnostics.CodeAnalysis;
using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using Microsoft.SqlTools.ServiceLayer.QueryExecution.Contracts;
using Microsoft.SqlTools.ServiceLayer.QueryExecution.DataStorage;
using Microsoft.SqlTools.ServiceLayer.SqlContext;
using Microsoft.SqlTools.ServiceLayer.UnitTests.Utility;
using Moq;
using Xunit;

namespace Microsoft.SqlTools.ServiceLayer.UnitTests.QueryExecution.DataStorage
{
    public class ServiceBufferReaderWriterTests
    {
        [Fact]
        public void ReaderStreamNull()
        {
            // If: I create a service buffer file stream reader with a null stream
            // Then: It should throw an exception
            Assert.Throws<ArgumentNullException>(() => new ServiceBufferFileStreamReader(null, new QueryExecutionSettings()));
        }

        [Fact]
        public void ReaderSettingsNull()
        {
            // If: I create a service buffer file stream reader with null settings
            // Then: It should throw an exception
            Assert.Throws<ArgumentNullException>(() => new ServiceBufferFileStreamReader(Stream.Null, null));
        }

        [Fact]
        public void ReaderInvalidStreamCannotRead()
        {
            // If: I create a service buffer file stream reader with a stream that cannot be read
            // Then: I should get an exception
            var invalidStream = new Mock<Stream>();
            invalidStream.SetupGet(s => s.CanRead).Returns(false);
            invalidStream.SetupGet(s => s.CanSeek).Returns(true);
            Assert.Throws<InvalidOperationException>(() =>
            {
                ServiceBufferFileStreamReader obj = new ServiceBufferFileStreamReader(invalidStream.Object, new QueryExecutionSettings());
                obj.Dispose();
            });
        }

        [Fact]
        public void ReaderInvalidStreamCannotSeek()
        {
            // If: I create a service buffer file stream reader with a stream that cannot seek
            // Then: I should get an exception
            var invalidStream = new Mock<Stream>();
            invalidStream.SetupGet(s => s.CanRead).Returns(true);
            invalidStream.SetupGet(s => s.CanSeek).Returns(false);
            Assert.Throws<InvalidOperationException>(() =>
            {
                ServiceBufferFileStreamReader obj = new ServiceBufferFileStreamReader(invalidStream.Object, new QueryExecutionSettings());
                obj.Dispose();
            });
        }

        [Fact]
        public void WriterStreamNull()
        {
            // If: I create a service buffer file stream writer with a null stream
            // Then: It should throw an exception
            Assert.Throws<ArgumentNullException>(() => new ServiceBufferFileStreamWriter(null, new QueryExecutionSettings()));
        }

        [Fact]
        public void WriterSettingsNull()
        {
            // If: I create a service buffer file stream writer with null settings
            // Then: It should throw an exception
            Assert.Throws<ArgumentNullException>(() => new ServiceBufferFileStreamWriter(Stream.Null, null));
        }

        [Fact]
        public void WriterInvalidStreamCannotWrite()
        {
            // If: I create a service buffer file stream writer with a stream that cannot be read
            // Then: I should get an exception
            var invalidStream = new Mock<Stream>();
            invalidStream.SetupGet(s => s.CanWrite).Returns(false);
            invalidStream.SetupGet(s => s.CanSeek).Returns(true);
            Assert.Throws<InvalidOperationException>(() =>
            {
                ServiceBufferFileStreamWriter obj = new ServiceBufferFileStreamWriter(invalidStream.Object, new QueryExecutionSettings());
                obj.Dispose();
            });
        }

        [Fact]
        public void WriterInvalidStreamCannotSeek()
        {
            // If: I create a service buffer file stream writer with a stream that cannot seek
            // Then: I should get an exception
            var invalidStream = new Mock<Stream>();
            invalidStream.SetupGet(s => s.CanWrite).Returns(true);
            invalidStream.SetupGet(s => s.CanSeek).Returns(false);
            Assert.Throws<InvalidOperationException>(() =>
            {
                ServiceBufferFileStreamWriter obj = new ServiceBufferFileStreamWriter(invalidStream.Object, new QueryExecutionSettings());
                obj.Dispose();
            });
        }

        [SuppressMessage("ReSharper", "UnusedParameter.Local")]
        private static string VerifyReadWrite<T>(int valueLength, T value, 
            Func<ServiceBufferFileStreamWriter, T, int> writeFunc, 
            Func<ServiceBufferFileStreamReader, long, FileStreamReadResult> readFunc,
            QueryExecutionSettings overrideSettings = null)
        {
            // Setup: Create a mock file stream
            byte[] storage = new byte[8192];
            overrideSettings = overrideSettings ?? new QueryExecutionSettings();
            const long rowId = 100;
            
            // If:
            // ... I write a type T to the writer
            using (ServiceBufferFileStreamWriter writer = new ServiceBufferFileStreamWriter(new MemoryStream(storage), overrideSettings))
            {
                int writtenBytes = writeFunc(writer, value);
                Assert.Equal(valueLength, writtenBytes);
            }

            // ... And read the type T back
            FileStreamReadResult outValue;
            using (ServiceBufferFileStreamReader reader = new ServiceBufferFileStreamReader(new MemoryStream(storage), overrideSettings))
            {
                outValue = readFunc(reader, rowId);
            }

            // Then:
            Assert.Equal(value, outValue.Value.RawObject);
            Assert.Equal(valueLength, outValue.TotalLength);
            Assert.NotNull(outValue.Value);

            // ... The id we set should be stored in the returned db cell value
            Assert.Equal(rowId, outValue.Value.RowId);

            return outValue.Value.DisplayValue;
        }

        [Theory]
        [InlineData(0)]
        [InlineData(10)]
        [InlineData(-10)]
        [InlineData(short.MaxValue)]    // Two byte number
        [InlineData(short.MinValue)]    // Negative two byte number
        public void Int16(short value)
        {
            VerifyReadWrite(sizeof(short) + 1, value, (writer, val) => writer.WriteInt16(val), (reader, rowId) => reader.ReadInt16(0, rowId));
        }

        [Theory]
        [InlineData(0)]
        [InlineData(10)]
        [InlineData(-10)]
        [InlineData(short.MaxValue)]    // Two byte number
        [InlineData(short.MinValue)]    // Negative two byte number
        [InlineData(int.MaxValue)]      // Four byte number
        [InlineData(int.MinValue)]      // Negative four byte number
        public void Int32(int value)
        {
            VerifyReadWrite(sizeof(int) + 1, value, (writer, val) => writer.WriteInt32(val), (reader, rowId) => reader.ReadInt32(0, rowId));
        }

        [Theory]
        [InlineData(0)]
        [InlineData(10)]
        [InlineData(-10)]
        [InlineData(short.MaxValue)]    // Two byte number
        [InlineData(short.MinValue)]    // Negative two byte number
        [InlineData(int.MaxValue)]      // Four byte number
        [InlineData(int.MinValue)]      // Negative four byte number
        [InlineData(long.MaxValue)]     // Eight byte number
        [InlineData(long.MinValue)]     // Negative eight byte number
        public void Int64(long value)
        {
            VerifyReadWrite(sizeof(long) + 1, value, (writer, val) => writer.WriteInt64(val), (reader, rowId) => reader.ReadInt64(0, rowId));
        }

        [Theory]
        [InlineData(0)]
        [InlineData(10)]
        public void Byte(byte value)
        {
            VerifyReadWrite(sizeof(byte) + 1, value, (writer, val) => writer.WriteByte(val), (reader, rowId) => reader.ReadByte(0, rowId));
        }

        [Theory]
        [InlineData('a')]
        [InlineData('1')]
        [InlineData((char)0x9152)]  // Test something in the UTF-16 space
        public void Char(char value)
        {
            VerifyReadWrite(sizeof(char) + 1, value, (writer, val) => writer.WriteChar(val), (reader, rowId) => reader.ReadChar(0, rowId));
        }

        [Theory]
        [InlineData(true, true)]
        [InlineData(false, true)]
        [InlineData(true, false)]
        [InlineData(false, false)]
        public void Boolean(bool value, bool preferNumeric)
        {
            string displayValue = VerifyReadWrite(sizeof(bool) + 1, value,
                (writer, val) => writer.WriteBoolean(val),
                (reader, rowId) => reader.ReadBoolean(0, rowId),
                new QueryExecutionSettings {DisplayBitAsNumber = preferNumeric}
            );

            // Validate the display value
            if (preferNumeric)
            {
                int output;
                Assert.True(int.TryParse(displayValue, out output));
            }
            else
            {
                bool output;
                Assert.True(bool.TryParse(displayValue, out output));
            }
        }

        [Theory]
        [InlineData(0)]
        [InlineData(10.1)]
        [InlineData(-10.1)]
        [InlineData(float.MinValue)]
        [InlineData(float.MaxValue)]
        [InlineData(float.PositiveInfinity)]
        [InlineData(float.NegativeInfinity)]
        public void Single(float value)
        {
            VerifyReadWrite(sizeof(float) + 1, value, (writer, val) => writer.WriteSingle(val), (reader, rowId) => reader.ReadSingle(0, rowId));
        }

        [Theory]
        [InlineData(0)]
        [InlineData(10.1)]
        [InlineData(-10.1)]
        [InlineData(float.MinValue)]
        [InlineData(float.MaxValue)]
        [InlineData(float.PositiveInfinity)]
        [InlineData(float.NegativeInfinity)]
        [InlineData(double.PositiveInfinity)]
        [InlineData(double.NegativeInfinity)]
        [InlineData(double.MinValue)]
        [InlineData(double.MaxValue)]
        public void Double(double value)
        {
            VerifyReadWrite(sizeof(double) + 1, value, (writer, val) => writer.WriteDouble(val), (reader, rowId) => reader.ReadDouble(0, rowId));
        }

        [Fact]
        public void SqlDecimalTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because SqlDecimal values can't be written as constant expressions
            SqlDecimal[] testValues =
            {
                SqlDecimal.MaxValue, SqlDecimal.MinValue, new SqlDecimal(0x01, 0x01, true, 0, 0, 0, 0)
            };
            foreach (SqlDecimal value in testValues)
            {
                int valueLength = 4 + value.BinData.Length;
                VerifyReadWrite(valueLength, value, (writer, val) => writer.WriteSqlDecimal(val), (reader, rowId) => reader.ReadSqlDecimal(0, rowId));
            }
        }

        [Fact]
        public void Decimal()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because Decimal values can't be written as constant expressions
            decimal[] testValues =
            {
                decimal.Zero, decimal.One, decimal.MinusOne, decimal.MinValue, decimal.MaxValue
            };

            foreach (decimal value in testValues)
            {
                int valueLength = decimal.GetBits(value).Length*4 + 1;
                VerifyReadWrite(valueLength, value, (writer, val) => writer.WriteDecimal(val), (reader, rowId) => reader.ReadDecimal(0, rowId));
            }
        }

        [Fact]
        public void DateTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because DateTime values can't be written as constant expressions
            DateTime[] testValues = 
            {
                DateTime.Now, DateTime.UtcNow, DateTime.MinValue, DateTime.MaxValue
            };

            // Setup: Create a DATE column
            DbColumnWrapper col = new DbColumnWrapper(new TestDbColumn {DataTypeName = "DaTe"});
            
            foreach (DateTime value in testValues)
            {
                string displayValue = VerifyReadWrite(sizeof(long) + 1, value, (writer, val) => writer.WriteDateTime(val),
                    (reader, rowId) => reader.ReadDateTime(0, rowId, col));

                // Make sure the display value does not have a time string
                Assert.True(Regex.IsMatch(displayValue, @"^[\d]{4}-[\d]{2}-[\d]{2}$"));
            }
        }

        [Fact]
        public void DateTimeTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because DateTime values can't be written as constant expressions
            DateTime[] testValues =
            {
                DateTime.Now, DateTime.UtcNow, DateTime.MinValue, DateTime.MaxValue
            };

            // Setup: Create a DATETIME column
            DbColumnWrapper col = new DbColumnWrapper(new TestDbColumn {DataTypeName = "DaTeTiMe"});

            foreach (DateTime value in testValues)
            {
                string displayValue = VerifyReadWrite(sizeof(long) + 1, value, (writer, val) => writer.WriteDateTime(val),
                    (reader, rowId) => reader.ReadDateTime(0, rowId, col));

                // Make sure the display value has a time string with 3 milliseconds
                Assert.True(Regex.IsMatch(displayValue, @"^[\d]{4}-[\d]{2}-[\d]{2} [\d]{2}:[\d]{2}:[\d]{2}\.[\d]{3}$"));
            }
        }

        [Theory]
        [InlineData(1)]
        [InlineData(2)]
        [InlineData(3)]
        [InlineData(4)]
        [InlineData(5)]
        [InlineData(6)]
        [InlineData(7)]
        public void DateTime2Test(int precision)
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because DateTime values can't be written as constant expressions
            DateTime[] testValues =
            {
                DateTime.Now, DateTime.UtcNow, DateTime.MinValue, DateTime.MaxValue
            };

            // Setup: Create a DATETIME column
            DbColumnWrapper col = new DbColumnWrapper(new TestDbColumn
            {
                DataTypeName = "DaTeTiMe2",
                NumericScale = precision
            });

            foreach (DateTime value in testValues)
            {
                string displayValue = VerifyReadWrite(sizeof(long) + 1, value, (writer, val) => writer.WriteDateTime(val),
                    (reader, rowId) => reader.ReadDateTime(0, rowId, col));

                // Make sure the display value has a time string with variable number of milliseconds
                Assert.True(Regex.IsMatch(displayValue, @"^[\d]{4}-[\d]{2}-[\d]{2} [\d]{2}:[\d]{2}:[\d]{2}"));
                if (precision > 0)
                {
                    Assert.True(Regex.IsMatch(displayValue, $@"\.[\d]{{{precision}}}$"));
                }
            }
        }

        [Fact]
        public void DateTime2ZeroScaleTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because DateTime values can't be written as constant expressions
            DateTime[] testValues =
            {
                DateTime.Now, DateTime.UtcNow, DateTime.MinValue, DateTime.MaxValue
            };

            // Setup: Create a DATETIME2 column
            DbColumnWrapper col = new DbColumnWrapper(new TestDbColumn {DataTypeName = "DaTeTiMe2", NumericScale = 0});

            foreach (DateTime value in testValues)
            {
                string displayValue = VerifyReadWrite(sizeof(long) + 1, value, (writer, val) => writer.WriteDateTime(val),
                    (reader, rowId) => reader.ReadDateTime(0, rowId, col));

                // Make sure the display value has a time string with 0 milliseconds
                Assert.True(Regex.IsMatch(displayValue, @"^[\d]{4}-[\d]{2}-[\d]{2} [\d]{2}:[\d]{2}:[\d]{2}$"));
            }
        }

        [Fact]
        public void DateTime2InvalidScaleTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because DateTime values can't be written as constant expressions
            DateTime[] testValues =
            {
                DateTime.Now, DateTime.UtcNow, DateTime.MinValue, DateTime.MaxValue
            };

            // Setup: Create a DATETIME2 column
            DbColumnWrapper col = new DbColumnWrapper(new TestDbColumn {DataTypeName = "DaTeTiMe2", NumericScale = 255});

            foreach (DateTime value in testValues)
            {
                string displayValue = VerifyReadWrite(sizeof(long) + 1, value, (writer, val) => writer.WriteDateTime(val),
                    (reader, rowId) => reader.ReadDateTime(0, rowId, col));

                // Make sure the display value has a time string with 7 milliseconds
                Assert.True(Regex.IsMatch(displayValue, @"^[\d]{4}-[\d]{2}-[\d]{2} [\d]{2}:[\d]{2}:[\d]{2}\.[\d]{7}$"));

            }
        }

        [Fact]
        public void DateTimeOffsetTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because DateTimeOffset values can't be written as constant expressions
            DateTimeOffset[] testValues =
            {
                DateTimeOffset.Now, DateTimeOffset.UtcNow, DateTimeOffset.MinValue, DateTimeOffset.MaxValue
            };
            foreach (DateTimeOffset value in testValues)
            {
                VerifyReadWrite(sizeof(long)*2 + 1, value, (writer, val) => writer.WriteDateTimeOffset(val),
                    (reader, rowId) => reader.ReadDateTimeOffset(0, rowId));
            }
        }

        [Fact]
        public void TimeSpanTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because TimeSpan values can't be written as constant expressions
            TimeSpan[] testValues =
            {
                TimeSpan.Zero, TimeSpan.MinValue, TimeSpan.MaxValue, TimeSpan.FromMinutes(60)
            };
            foreach (TimeSpan value in testValues)
            {
                VerifyReadWrite(sizeof(long) + 1, value, (writer, val) => writer.WriteTimeSpan(val),
                    (reader, rowId) => reader.ReadTimeSpan(0, rowId));
            }
        }

        [Fact]
        public void StringNullTest()
        {
            // Setup: Create a mock file stream
            using (MemoryStream stream = new MemoryStream(new byte[8192]))
            {
                // If:
                // ... I write null as a string to the writer
                using (ServiceBufferFileStreamWriter writer = new ServiceBufferFileStreamWriter(stream, new QueryExecutionSettings()))
                {
                    // Then:
                    // ... I should get an argument null exception
                    Assert.Throws<ArgumentNullException>(() => writer.WriteString(null));
                }
            }
        }

        [Theory]
        [InlineData(0, null)]                             // Test of empty string
        [InlineData(1, new[] { 'j' })]
        [InlineData(1, new[] { (char)0x9152 })]
        [InlineData(100, new[] { 'j', (char)0x9152 })]    // Test alternating utf-16/ascii characters
        [InlineData(512, new[] { 'j', (char)0x9152 })]    // Test that requires a 4 byte length
        public void StringTest(int length, char[] values)
        {
            // Setup: 
            // ... Generate the test value
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++)
            {
                sb.Append(values[i%values.Length]);
            }
            string value = sb.ToString();
            int lengthLength = length == 0 || length > 255 ? 5 : 1;
            VerifyReadWrite(sizeof(char)*length + lengthLength, value, (writer, val) => writer.WriteString(value),
                (reader, rowId) => reader.ReadString(0, rowId));
        }

        [Fact]
        public void BytesNullTest()
        {
            // Setup: Create a mock file stream wrapper
            using (MemoryStream stream = new MemoryStream(new byte[8192]))
            {
                // If:
                // ... I write null as a string to the writer
                using (ServiceBufferFileStreamWriter writer = new ServiceBufferFileStreamWriter(stream, new QueryExecutionSettings()))
                {
                    // Then:
                    // ... I should get an argument null exception
                    Assert.Throws<ArgumentNullException>(() => writer.WriteBytes(null));
                }
            }
        }

        [Theory]
        [InlineData(0, new byte[] { 0x00 })]                  // Test of empty byte[]
        [InlineData(1, new byte[] { 0x00 })]
        [InlineData(1, new byte[] { 0xFF })]
        [InlineData(100, new byte[] { 0x10, 0xFF, 0x00 })] 
        [InlineData(512, new byte[] { 0x10, 0xFF, 0x00 })]    // Test that requires a 4 byte length
        public void Bytes(int length, byte[] values)
        {
            // Setup: 
            // ... Generate the test value
            List<byte> sb = new List<byte>();
            for (int i = 0; i < length; i++)
            {
                sb.Add(values[i % values.Length]);
            }
            byte[] value = sb.ToArray();
            int lengthLength = length == 0 || length > 255 ? 5 : 1;
            int valueLength = sizeof(byte)*length + lengthLength;
            VerifyReadWrite(valueLength, value, (writer, val) => writer.WriteBytes(value),
                (reader, rowId) => reader.ReadBytes(0, rowId));
        }

        public static IEnumerable<object[]> GuidTestParameters
        {
            get
            {
                yield return new object[] {Guid.Empty};
                yield return new object[] {Guid.NewGuid()};
                yield return new object[] {Guid.NewGuid()};
            }
        }

        [Theory]
        [MemberData(nameof(GuidTestParameters))]
        public void GuidTest(Guid testValue)
        {
            VerifyReadWrite(testValue.ToByteArray().Length + 1, testValue,
                (writer, val) => writer.WriteGuid(testValue),
                (reader, rowId) => reader.ReadGuid(0, rowId));
        }

        [Fact]
        public void MoneyTest()
        {
            // Setup: Create some test values
            // NOTE: We are doing these here instead of InlineData because SqlMoney can't be written as a constant expression
            SqlMoney[] monies =
            {
                SqlMoney.Zero, SqlMoney.MinValue, SqlMoney.MaxValue, new SqlMoney(1.02)
            };
            foreach (SqlMoney money in monies)
            {
                VerifyReadWrite(sizeof(decimal) + 1, money, (writer, val) => writer.WriteMoney(money),
                    (reader, rowId) => reader.ReadMoney(0, rowId));
            }
        }
    }
}